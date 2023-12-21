package ru.mironov.projects.noPassword.models.user;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Size;
import ru.mironov.projects.noPassword.models.password.Password;

import java.util.List;

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

    public User() {
    }

    public User(String username, String password, String email, List<Password> passwords, UserRole role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.passwords = passwords;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Password> getPasswords() {
        return passwords;
    }

    public void setPasswords(List<Password> passwords) {
        this.passwords = passwords;
    }
}
