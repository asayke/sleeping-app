package ru.asayke.jwtappdemo.util;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.asayke.jwtappdemo.models.User;
import ru.asayke.jwtappdemo.service.UserService;

@Component
@AllArgsConstructor
public class UserValidator implements Validator {
    private final UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        if(userService.findByUsername(user.getUsername()) != null)
            errors.rejectValue("username", "The username is already taken");

        if(userService.findByEmail(user.getEmail()) != null)
            errors.rejectValue("email", "The email is already taken");

        if(user.getUsername() == null)
            errors.rejectValue("username", "Username can't be null");

        if(user.getEmail() == null)
            errors.rejectValue("email", "Email can't be null");

        if(user.getFullName() == null)
            errors.rejectValue("fullname", "Firstname can't be null");

        if(user.getPassword() == null)
            errors.rejectValue("password", "Firstname can't be null");
    }
}
