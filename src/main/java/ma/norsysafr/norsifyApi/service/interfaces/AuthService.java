package ma.norsysafr.norsifyApi.service.interfaces;



import ma.norsysafr.norsifyApi.entities.token.JwtTokenRequest;

import java.util.Map;

public interface AuthService {
    Map<String,String> generateJwtToken(JwtTokenRequest jwtTokenRequest);
}
