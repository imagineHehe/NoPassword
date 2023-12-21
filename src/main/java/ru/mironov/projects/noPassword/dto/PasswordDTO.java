package ru.mironov.projects.noPassword.dto;

import lombok.Getter;
import lombok.Setter;
import ru.mironov.projects.noPassword.models.password.ApplicationName;

@Getter
@Setter
public class PasswordDTO {
    private ApplicationName applicationName;

    private String username;

    private String login;

    private String password;

    private String token;

    private UserDTO userDTO;
}
