package ma.norsysafr.norsifyApi.dto.request;

import ma.norsysafr.norsifyApi.entities.user.AppRole;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * A DTO for the {@link AppRole}
 */
public record AppRoleDtoRequest(
        @NotNull(message = "Role name is required")
        @NotBlank(message = "Role name cannot be blank")
        String roleName
)
implements Serializable {
}
