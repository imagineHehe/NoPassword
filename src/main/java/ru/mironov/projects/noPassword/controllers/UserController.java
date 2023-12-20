package ru.mironov.projects.noPassword.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.mironov.projects.noPassword.models.user.User;
import ru.mironov.projects.noPassword.security.UserDetailsImpl;
import ru.mironov.projects.noPassword.services.UserServiceJPA;
import ru.mironov.projects.noPassword.util.UserErrorResponse;
import ru.mironov.projects.noPassword.util.UserNotFoundException;

import java.util.List;


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
    public User userPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return userDetails.getUser();
    }

    //Admin control
    @GetMapping("/admin/users")
    @ResponseBody
    public List<User> usersList() {
        return userServiceJPA.findAll();
    }

    @GetMapping("/admin/user/{id}")
    @ResponseBody
    public User oneUser(@PathVariable("id") int id) {
        return userServiceJPA.findOne(id);
    }

    //Handler
    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserNotFoundException e) {
        UserErrorResponse response = new UserErrorResponse(String.format("User с id %d не найден", e.getId()), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
