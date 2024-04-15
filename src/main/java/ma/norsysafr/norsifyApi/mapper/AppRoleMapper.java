package ma.norsysafr.norsifyApi.mapper;

import ma.norsysafr.norsifyApi.dto.response.AppRoleDtoResponse;
import ma.norsysafr.norsifyApi.entities.user.AppRole;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AppRoleMapper {
    AppRoleMapper INSTANCE = Mappers.getMapper(AppRoleMapper.class);

    AppRoleDtoResponse roleToAppRoleDtoResponse(AppRole appRole);
}
