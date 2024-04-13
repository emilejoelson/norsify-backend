package ma.norsysafr.norsifyApi.service.implementation;


import ma.norsysafr.norsifyApi.entities.token.JwtToken;
import ma.norsysafr.norsifyApi.entities.token.JwtTokenRequest;
import ma.norsysafr.norsifyApi.service.interfaces.AuthService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {


    private JwtEncoder jwtEncoder;
    private JwtDecoder jwtDecoder;
    private UserDetailsService userDetailsService;
    private AuthenticationManager authenticationManager;

    public AuthServiceImpl(JwtEncoder jwtEncoder, AuthenticationManager authenticationManager, JwtDecoder jwtDecoder, UserDetailsService userDetailsService){
        this.jwtEncoder = jwtEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtDecoder = jwtDecoder;
        this.userDetailsService = userDetailsService;
    }


    @Override
    public Map<String, String> generateJwtToken(JwtTokenRequest jwtTokenRequest) {
        JwtToken jwtToken = generateJwtTokenInternal(jwtTokenRequest);

        Map<String, String> idToken = new HashMap<>();
        idToken.put("accessToken", jwtToken.getAccessToken());
        if (jwtTokenRequest.isWithRefreshToken()) {
            idToken.put("refreshToken", jwtToken.getRefreshToken());
        }
        return idToken;
    }

    private JwtToken generateJwtTokenInternal(JwtTokenRequest jwtTokenRequest) {
        String subject = null;
        String scope = null;

        if( jwtTokenRequest.getGrantType().equals("password")){
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(jwtTokenRequest.getUsername(), jwtTokenRequest.getPassword())
            );
            subject = authentication.getName();
            scope = authentication.getAuthorities()
                    .stream().map(aut -> aut.getAuthority())
                    .collect(Collectors.joining(" "));

        } else if(jwtTokenRequest.getGrantType().equals("refreshToken")){
            if(jwtTokenRequest.getRefreshToken() == null){
                throw new IllegalArgumentException("Refresh Token is required");
            }
            Jwt decodeJWT = null;
            try{
                decodeJWT = jwtDecoder.decode(jwtTokenRequest.getRefreshToken());
            } catch (JwtException e){
                throw new IllegalArgumentException(e.getMessage());
            }

            subject = decodeJWT.getSubject();
            UserDetails userDetails = userDetailsService.loadUserByUsername(subject);
            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
            scope = authorities.stream().map(auth -> auth.getAuthority()).collect(Collectors.joining(" "));
        }

        Instant instant = Instant.now();

        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .subject(subject)
                .issuedAt(instant)
                .expiresAt(instant.plus(jwtTokenRequest.isWithRefreshToken() ? 1 : 5, ChronoUnit.MINUTES))
                .issuer("security-service")
                .claim("scope", scope)
                .build();

        String jwtAccessToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();

        String jwtRefreshToken = null;
        if(jwtTokenRequest.isWithRefreshToken()){
            JwtClaimsSet jwtClaimsSetRefresh = JwtClaimsSet.builder()
                    .subject(subject)
                    .issuedAt(instant)
                    .expiresAt(instant.plus(5, ChronoUnit.MINUTES))
                    .issuer("security-service")
                    .build();

            jwtRefreshToken = jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSetRefresh)).getTokenValue();
        }

        return JwtToken.builder()
                .accessToken(jwtAccessToken)
                .refreshToken(jwtRefreshToken)
                .build();
    }
}
