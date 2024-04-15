package ma.norsysafr.norsifyApi.controller;

import lombok.Data;
import ma.norsysafr.norsifyApi.dto.request.AppRoleDtoRequest;
import ma.norsysafr.norsifyApi.dto.request.AppUserDtoRequest;
import ma.norsysafr.norsifyApi.dto.response.AppRoleDtoResponse;
import ma.norsysafr.norsifyApi.dto.response.AppUserDtoResponse;
import ma.norsysafr.norsifyApi.entities.user.AppRole;
import ma.norsysafr.norsifyApi.entities.user.AppUser;
import ma.norsysafr.norsifyApi.service.implementation.AccountServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class AccountRestController {
    private AccountServiceImpl accountService;

    public AccountRestController(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    @GetMapping(path = "/users")
    public ResponseEntity<List<AppUserDtoResponse>> listUsers() {
        List<AppUserDtoResponse> allUsers = accountService.listUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }


    @PostMapping(path = "/users")
    public ResponseEntity<AppUserDtoResponse> addNewUser(@Valid @RequestBody AppUserDtoRequest appUserDto) {
        AppUserDtoResponse response = accountService.addNewUser(appUserDto);
        return ResponseEntity.created(URI.create("/users/"+response.id()))
                .body(response);
    }


    @PostMapping(path = "/roles")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public ResponseEntity<AppRoleDtoResponse> addNewRole(@Valid @RequestBody AppRoleDtoRequest appRoleDto) {
        AppRoleDtoResponse response = accountService.addNewRole(appRoleDto);
        return  ResponseEntity.created(URI.create("/roles/"+response.id()))
                .body(response);
    }

    @GetMapping(path = "/roles")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public ResponseEntity<List<AppRoleDtoResponse>> listRoles() {
        List<AppRoleDtoResponse> responses = accountService.listRoles();
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @PostMapping(path = "/addRoleToUser")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public ResponseEntity<Void> addRoleToUser(@RequestBody RoleUserForm roleUserForm) {
        accountService.addRoleToUser(roleUserForm.getUsername(), roleUserForm.getRoleName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Data
    public static class RoleUserForm {
        private String username;
        private String roleName;
    }
}
