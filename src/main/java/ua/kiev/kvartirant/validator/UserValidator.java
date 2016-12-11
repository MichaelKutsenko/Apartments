package ua.kiev.kvartirant.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ua.kiev.kvartirant.model.User;
import ua.kiev.kvartirant.service.UserService;

/**
 * Validate fields of new {@link User}. Implementation of {@link Validator} interface.
 */

public class UserValidator implements Validator {

    @Autowired
    UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "required");
        if (user.getUsername().length() < 6 || user.getUsername().length() > 32){
            errors.rejectValue("username", "size.userForm");
        }
        if (userService.findUserByUserName(user.getUsername()) != null){
            errors.rejectValue("username", "notUnique.userName.userForm");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required");
        if (user.getPassword().length() < 6 || user.getPassword().length() > 32){
            errors.rejectValue("password", "size.userForm");
        }
        if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword", "different.userForm.password");
        }
    }
}
