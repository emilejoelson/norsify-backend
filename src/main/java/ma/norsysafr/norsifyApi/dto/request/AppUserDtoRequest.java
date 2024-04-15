package ma.norsysafr.norsifyApi.dto.request;

import java.io.Serializable;

import ma.norsysafr.norsifyApi.entities.user.AppUser;
import ma.norsysafr.norsifyApi.validation.PasswordValid;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * A DTO for the {@link AppUser}
 */

public record AppUserDtoRequest(
        @NotEmpty(message = "Username is required")
        @NotBlank(message = "Username must not be blank")
        @Size(min=6,message = "Username should have at least {min} characters")
        String username,
        @NotEmpty(message = "Password is required")
        @NotBlank(message = "Password must not be blank")
        @PasswordValid
        String password
)
implements Serializable {
}
