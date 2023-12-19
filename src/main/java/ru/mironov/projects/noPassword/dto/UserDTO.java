package ru.mironov.projects.noPassword.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public class UserDTO {
    @NotEmpty(message = "Имя пользователя не может быть пустым")
    private String username;
    @NotEmpty(message = "Пароль не может быть пустым")
    @Size(min = 3, message = "Пароль должен состоять минимум из 3 символов")
    private String password;
    @NotEmpty(message = "Почта не может быть пустой")
    @Size(min = 10, message = "Данное поле должно содержать минимум 10 символов")
    private String email;

    private List<PasswordDTO> passwords;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<PasswordDTO> getPasswords() {
        return passwords;
    }

    public void setPasswords(List<PasswordDTO> passwords) {
        this.passwords = passwords;
    }
}
