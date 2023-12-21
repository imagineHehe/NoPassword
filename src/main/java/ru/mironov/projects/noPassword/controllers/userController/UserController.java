package ru.mironov.projects.noPassword.controllers.userController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.mironov.projects.noPassword.models.user.User;
import ru.mironov.projects.noPassword.security.UserDetailsImpl;
import ru.mironov.projects.noPassword.services.UserServiceJPA;


@RestController
@RequestMapping("")
public class UserController {
    private final UserServiceJPA userServiceJPA;

    @Autowired
    public UserController(UserServiceJPA userServiceJPA) {
        this.userServiceJPA = userServiceJPA;
    }

    //User control
    @GetMapping("/user")
    @ResponseBody
    public User getUser() {
        return GetUser.execute();
    }

}
