package ma.norsysafr.norsifyApi.dto.response;

import ma.norsysafr.norsifyApi.entities.user.AppRole;

import java.io.Serializable;

/**
 * A DTO for the {@link AppRole}
 */
public record AppRoleDtoResponse(Long id,String roleName) implements Serializable {
}
