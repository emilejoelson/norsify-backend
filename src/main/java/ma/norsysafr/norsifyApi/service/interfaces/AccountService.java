package ma.norsysafr.norsifyApi.service.interfaces;


import ma.norsysafr.norsifyApi.dto.request.AppRoleDtoRequest;
import ma.norsysafr.norsifyApi.dto.request.AppUserDtoRequest;
import ma.norsysafr.norsifyApi.dto.response.AppRoleDtoResponse;
import ma.norsysafr.norsifyApi.dto.response.AppUserDtoResponse;
import ma.norsysafr.norsifyApi.entities.user.AppRole;
import ma.norsysafr.norsifyApi.entities.user.AppUser;

import java.util.List;

public interface AccountService {
    AppUserDtoResponse addNewUser(AppUserDtoRequest appUserDto);
    AppRoleDtoResponse addNewRole(AppRoleDtoRequest appRoleDto);
    void addRoleToUser(String username, String roleName);
    AppUserDtoResponse loadUserByUsername(String username);
    List<AppUserDtoResponse> listUsers();
    List<AppRoleDtoResponse> listRoles();
}
