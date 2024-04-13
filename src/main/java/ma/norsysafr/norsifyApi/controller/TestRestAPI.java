package ma.norsysafr.norsifyApi.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TestRestAPI {

    @GetMapping("/dataTest")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public Map<String,Object> dataTest(Authentication authentication){
        return Map.of(
                "message","Data Test",
                "username",authentication.getName(),
                "authrorities",authentication.getAuthorities()
        );
    }

}
