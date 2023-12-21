package ru.mironov.projects.noPassword.util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.mironov.projects.noPassword.dto.UserDTO;
import ru.mironov.projects.noPassword.models.user.User;
import ru.mironov.projects.noPassword.services.UserServiceJPA;

@Component
@RequiredArgsConstructor
public class UserValidator implements Validator {
    private final UserServiceJPA userServiceJPA;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;
        if (userServiceJPA.isUserExists(user.getUsername(), user.getEmail())) {
            errors.rejectValue("username", "",
                    "Пользователь с таким логином или почтой уже зарегистрирован");
        }
    }
}
