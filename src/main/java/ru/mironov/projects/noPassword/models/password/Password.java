package ru.mironov.projects.noPassword.models.password;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.mironov.projects.noPassword.models.user.User;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "password")
public class Password {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    private ApplicationName applicationName;
    @Column(name = "username")
    private String username;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "token")
    private String token;
    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User owner;

    public Password(ApplicationName applicationName, String username, String login, String password, String token, User owner) {
        this.applicationName = applicationName;
        this.username = username;
        this.login = login;
        this.password = password;
        this.token = token;
        this.owner = owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
        owner.addPassword(this);
    }
}
