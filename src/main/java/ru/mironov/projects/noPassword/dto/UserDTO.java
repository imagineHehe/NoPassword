package ru.mironov.projects.noPassword.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter
@Getter
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
}
