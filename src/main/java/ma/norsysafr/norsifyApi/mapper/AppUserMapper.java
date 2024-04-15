package ma.norsysafr.norsifyApi.mapper;

import ma.norsysafr.norsifyApi.dto.response.AppUserDtoResponse;
import ma.norsysafr.norsifyApi.entities.user.AppRole;
import ma.norsysafr.norsifyApi.entities.user.AppUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.Collection;
import java.util.stream.Collectors;

@Mapper
public interface AppUserMapper {
    AppUserMapper INSTANCE = Mappers.getMapper(AppUserMapper.class);

    @Mapping(source = "appRoles", target = "appRoles")
    AppUserDtoResponse appUserToAppUserDtoResponse(AppUser appUser);

    default Collection<String> mapRoles(Collection<AppRole> roles) {
        return roles.stream().map(AppRole::getRoleName).collect(Collectors.toList());
    }

}
