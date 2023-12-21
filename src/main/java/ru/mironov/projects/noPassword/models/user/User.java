package ru.mironov.projects.noPassword.models.user;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.mironov.projects.noPassword.models.password.Password;

import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Имя пользователя не может быть пустым")
    @Column(name = "username")
    private String username;
    @NotEmpty(message = "Пароль не может быть пустым")
    @Size(min = 3, message = "Пароль должен состоять минимум из 3 символов")
    @Column(name = "password")
    private String password;
    @NotEmpty(message = "Почта не может быть пустой")
    @Size(min = 10, message = "Данное поле должно содержать минимум 10 символов")
    @Column(name = "email")
    private String email;
    @JsonManagedReference
    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Password> passwords;
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role;


    public User(String username, String password, String email, List<Password> passwords, UserRole role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.passwords = passwords;
        this.role = role;
    }

    public void addPassword(Password password) {
        if (passwords == null)
            passwords = new ArrayList<>();
        passwords.add(password);
    }
}
