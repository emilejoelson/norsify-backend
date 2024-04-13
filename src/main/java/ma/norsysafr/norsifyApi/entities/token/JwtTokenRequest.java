package ma.norsysafr.norsifyApi.entities.token;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtTokenRequest {
    private String grantType;
    private String username;
    private String password;
    private boolean withRefreshToken;
    private String refreshToken;
}
