package ru.mironov.projects.noPassword.dto;

import ru.mironov.projects.noPassword.models.password.ApplicationName;

public class PasswordDTO {
    private ApplicationName applicationName;

    private String username;

    private String login;

    private String password;

    private String token;

    private UserDTO userDTO;


    public ApplicationName getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(ApplicationName applicationName) {
        this.applicationName = applicationName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}
