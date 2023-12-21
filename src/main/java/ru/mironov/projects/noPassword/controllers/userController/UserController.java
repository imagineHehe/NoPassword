package ru.mironov.projects.noPassword.controllers.userController;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.mironov.projects.noPassword.models.password.Password;
import ru.mironov.projects.noPassword.models.user.User;
import ru.mironov.projects.noPassword.security.UserDetailsImpl;
import ru.mironov.projects.noPassword.services.UserServiceJPA;

import java.net.URI;


@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceJPA userServiceJPA;
    private final GetUser getUser;
    private final AddAccount addAccount;


    //User control
    @GetMapping("/user")
    @ResponseBody
    public User getUser() {
        return getUser.execute();
    }
    @PostMapping("/user")
    public ResponseEntity<HttpStatus> addAccount(@RequestBody Password password) {
        return addAccount.execute(password);
    }

}
