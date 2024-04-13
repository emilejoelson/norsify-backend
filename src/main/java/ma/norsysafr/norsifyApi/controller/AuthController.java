package ma.norsysafr.norsifyApi.controller;


import ma.norsysafr.norsifyApi.entities.token.JwtTokenRequest;
import ma.norsysafr.norsifyApi.service.interfaces.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthController {

    private AuthService authService;
    public AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/token")
    public ResponseEntity<Map<String,String>> generateJwtToken(@RequestBody JwtTokenRequest jwtTokenRequest)
    {
        Map<String,String> response = authService.generateJwtToken(jwtTokenRequest);
        return  new ResponseEntity<>(response,HttpStatus.OK);
    }

}
