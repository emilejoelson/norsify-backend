package ma.norsysafr.norsifyApi.validation;

import ma.norsysafr.norsifyApi.exception.InvalidPasswordException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordValid, String> {
    private static final String PASSWORD_PATTERN = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        if (password == null || !password.matches(PASSWORD_PATTERN)) {
            throw new InvalidPasswordException("Password should contain at least one uppercase letter, one lowercase letter, one number, and one special character.");
        }
        return true;
    }

    @Override
    public void initialize(PasswordValid constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
