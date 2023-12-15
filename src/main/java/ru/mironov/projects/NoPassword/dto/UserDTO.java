package ru.mironov.projects.NoPassword.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserDTO {
    @NotEmpty(message = "Имя пользователя не может быть пустым")
    private String username;
    @NotEmpty(message = "Пароль не может быть пустым")
    @Size(min = 3, message = "Пароль должен состоять минимум из 3 символов")
    private String password;



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
