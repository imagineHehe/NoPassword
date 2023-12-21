package ru.mironov.projects.noPassword.controllers.userController;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.mironov.projects.noPassword.client.TokenGeneratorApi;
import ru.mironov.projects.noPassword.client.model.TokenRequest;
import ru.mironov.projects.noPassword.models.password.Password;
import ru.mironov.projects.noPassword.models.user.User;
import ru.mironov.projects.noPassword.security.UserDetailsImpl;
import ru.mironov.projects.noPassword.services.PasswordService;

import java.net.URI;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AddAccount {
    private final PasswordService passwordService;
    private final TokenGeneratorApi tokenGenerator;
    public ResponseEntity<HttpStatus> execute(Password password){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        addTokenAndCreate(password, userDetails.getUser());
        return ResponseEntity.created(URI.create("http://localhost:8080/user")).build();
    }

    private Password addTokenAndCreate(Password password, User owner){
        password.setToken(
                tokenGenerator.getToken(new TokenRequest(
                        password.getApplicationName(),
                        password.getLogin(),
                        password.getPassword())).token());
        password.setOwner(owner);
        owner.setPasswords(Collections.singletonList(password));
        passwordService.save(password);
        return password;
    }
}
