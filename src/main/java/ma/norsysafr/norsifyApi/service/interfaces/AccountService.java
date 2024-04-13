package ma.norsysafr.norsifyApi.service.interfaces;


import ma.norsysafr.norsifyApi.entities.user.AppRole;
import ma.norsysafr.norsifyApi.entities.user.AppUser;

import java.util.List;

public interface AccountService {
    AppUser addNewUser(AppUser appUser);
    AppRole addNewRole(AppRole appRole);
    void addRoleToUser(String username,String roleName);
    AppUser loadUserByUsername(String username);
    List<AppUser> listUsers();
    List<AppRole> listRoles();
}
