package ru.mironov.projects.noPassword.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.mironov.projects.noPassword.dto.UserDTO;
import ru.mironov.projects.noPassword.models.user.User;
import ru.mironov.projects.noPassword.services.UserServiceJPA;

@Component
public class UserValidator implements Validator {
    private final UserServiceJPA userServiceJPA;

    @Autowired
    public UserValidator(UserServiceJPA userServiceJPA) {
        this.userServiceJPA = userServiceJPA;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return UserDTO.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserDTO user = (UserDTO) o;
        if (userServiceJPA.isUserExists(user.getUsername(), user.getEmail())) {
            errors.rejectValue("username", "",
                    "Пользователь с таким логином или почтой уже зарегистрирован");
        }
    }
}
