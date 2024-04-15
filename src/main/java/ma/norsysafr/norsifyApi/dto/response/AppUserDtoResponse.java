package ma.norsysafr.norsifyApi.dto.response;

import java.io.Serializable;
import java.util.Collection;
import ma.norsysafr.norsifyApi.entities.user.AppUser;
/**
 *A DTO for the {@link AppUser}
 */
public record AppUserDtoResponse(Long id,String username,  Collection<AppRoleDtoResponse> appRoles) implements Serializable {
}
