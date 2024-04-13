package ma.norsysafr.norsifyApi.controller;

import lombok.Data;
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

import java.util.List;

@RestController
public class AccountRestController {
    private AccountServiceImpl accountService;

    public AccountRestController(AccountServiceImpl accountService) {
        this.accountService = accountService;
    }

    @GetMapping(path = "/users")
    public ResponseEntity<List<AppUser>> listUsers() {
        List<AppUser> allUsers = accountService.listUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }


    @PostMapping(path = "/users")
    public ResponseEntity<AppUser> addNewUser(@RequestBody AppUser appUser) {
        AppUser appuser = accountService.addNewUser(appUser);
        return new ResponseEntity<>(appuser, HttpStatus.OK);
    }

    @PostMapping(path = "/roles")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public ResponseEntity<AppRole> addNewRole(@RequestBody AppRole appRole) {
        AppRole approle = accountService.addNewRole(appRole);
        return new ResponseEntity<>(approle, HttpStatus.OK);
    }

    @GetMapping(path = "/roles")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public ResponseEntity<List<AppRole>> listRoles() {
        List<AppRole> allRoles = accountService.listRoles();
        return new ResponseEntity<>(allRoles, HttpStatus.OK);
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
