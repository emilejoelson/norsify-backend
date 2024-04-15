package ma.norsysafr.norsifyApi.service.implementation;

import ma.norsysafr.norsifyApi.dto.request.AppRoleDtoRequest;
import ma.norsysafr.norsifyApi.dto.request.AppUserDtoRequest;
import ma.norsysafr.norsifyApi.dto.response.AppRoleDtoResponse;
import ma.norsysafr.norsifyApi.dto.response.AppUserDtoResponse;
import ma.norsysafr.norsifyApi.entities.user.AppRole;
import ma.norsysafr.norsifyApi.entities.user.AppUser;
import ma.norsysafr.norsifyApi.exception.InvalidPasswordException;
import ma.norsysafr.norsifyApi.mapper.AppRoleMapper;
import ma.norsysafr.norsifyApi.mapper.AppUserMapper;
import ma.norsysafr.norsifyApi.repository.AppRoleRepository;
import ma.norsysafr.norsifyApi.repository.AppUserRepository;
import ma.norsysafr.norsifyApi.service.interfaces.AccountService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private final AppUserRepository appUserRepository;
    private final AppRoleRepository appRoleRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountServiceImpl(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public AppUserDtoResponse addNewUser(AppUserDtoRequest appUserDto) {
        if (!isPasswordValid(appUserDto.password())) {
            throw new InvalidPasswordException("Password should contain at least one uppercase letter, one lowercase letter, one number, and one special character.");
        }

        // Map Dto to entity
        AppUser appUser = new AppUser();
        appUser.setUsername(appUserDto.username());
        appUser.setPassword(passwordEncoder.encode(appUserDto.password()));

        // Save in Database
        AppUser savedAppUser = appUserRepository.save(appUser);

        // Map the saved entity back to Dto and return
        return AppUserMapper.INSTANCE.appUserToAppUserDtoResponse(savedAppUser);
    }

    private boolean isPasswordValid(String password) {
        String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        return password != null && password.matches(PASSWORD_PATTERN);
    }


    @Override
    public AppRoleDtoResponse addNewRole(AppRoleDtoRequest appRoleDto) {
        // Map Dto to entity
        AppRole appRole = new AppRole();
        appRole.setRoleName(appRoleDto.roleName());

        // Save in the Database
        AppRole savedAppRole = appRoleRepository.save(appRole);

        return AppRoleMapper.INSTANCE.roleToAppRoleDtoResponse(savedAppRole);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser appUser = appUserRepository.findByUsername(username);
        AppRole appRole = appRoleRepository.findByRoleName(roleName);
        appUser.getAppRoles().add(appRole);
        appUserRepository.save(appUser);
    }

    @Override
    public AppUserDtoResponse loadUserByUsername(String username) {
        AppUser appUser = appUserRepository.findByUsername(username);
        return AppUserMapper.INSTANCE.appUserToAppUserDtoResponse(appUser);
    }

    @Override
    public List<AppUserDtoResponse> listUsers() {
        return appUserRepository.findAll().stream()
                .map(AppUserMapper.INSTANCE::appUserToAppUserDtoResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AppRoleDtoResponse> listRoles() {
        return appRoleRepository.findAll().stream()
                .map(AppRoleMapper.INSTANCE::roleToAppRoleDtoResponse)
                .collect(Collectors.toList());
    }
}
