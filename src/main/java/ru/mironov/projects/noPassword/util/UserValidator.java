package ru.mironov.projects.noPassword.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.mironov.projects.noPassword.models.user.User;
import ru.mironov.projects.noPassword.services.UserService;

@Component
public class UserValidator implements Validator {
    private final UserService userService;

    @Autowired
    public UserValidator(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        if (userService.isUserExists(user.getUsername()))
            errors.rejectValue("username", "", "Пользователь с таким логином уже зарегистрирован");
    }
}
