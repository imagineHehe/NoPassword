package ru.mironov.projects.noPassword.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.mironov.projects.noPassword.dto.UserDTO;
import ru.mironov.projects.noPassword.models.user.User;
import ru.mironov.projects.noPassword.services.UserServiceJPA;
import ru.mironov.projects.noPassword.util.UserErrorResponse;
import ru.mironov.projects.noPassword.util.UserNotCreatedException;
import ru.mironov.projects.noPassword.util.UserValidator;

import java.util.List;

@RestController
@RequestMapping("")
public class AuthController {
    private final UserServiceJPA userServiceJPA;
    private final UserValidator userValidator;

    @Autowired
    public AuthController(UserServiceJPA userServiceJPA, UserValidator userValidator) {
        this.userServiceJPA = userServiceJPA;
        this.userValidator = userValidator;
    }


    @GetMapping("/login")
    public ResponseEntity<HttpStatus> login() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.getPrincipal().equals("anonymousUser")) {
            System.out.println("Авторизация выполнена");
            return new ResponseEntity<HttpStatus>(HttpStatus.FOUND);
        }
        return ResponseEntity.ok(HttpStatus.OK); //"Это страница логина, совершите Post запрос для входа";
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registration(@RequestBody @Valid User registeredUser,
                                                   BindingResult bindingResult) {
        userValidator.validate(registeredUser, bindingResult);
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField()).append(" - ").append(error.getDefaultMessage()).append(";");
            }
            throw new UserNotCreatedException(errorMsg.toString());
        }

        userServiceJPA.save(registeredUser);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<UserErrorResponse> handleException(UserNotCreatedException e) {
        UserErrorResponse response = new UserErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
