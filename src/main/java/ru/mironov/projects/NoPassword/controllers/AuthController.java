package ru.mironov.projects.NoPassword.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.mironov.projects.NoPassword.dto.UserDTO;
import ru.mironov.projects.NoPassword.models.user.User;
import ru.mironov.projects.NoPassword.services.UserService;
import ru.mironov.projects.NoPassword.util.UserValidator;


@Controller
@RequestMapping("removable")
public class AuthController {
    private final UserService userService;
    private final UserValidator userValidator;

    @Autowired
    public AuthController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    @GetMapping("/login")
    public String login(){
        return "auth/login";
    }
    @GetMapping("/registrations")
    public String registrationPage(@ModelAttribute("user") User userBlank){
        return "auth/registration";
    }
    @PostMapping("/registrations")
    public String performRegistration(@ModelAttribute("user") @Valid User newUser,
                                      BindingResult bindingResult){
        userValidator.validate(newUser, bindingResult);
        if(bindingResult.hasErrors()){
            return "auth/registration";
        }
        userService.register(newUser);

        return "redirect:/login";
    }
}
