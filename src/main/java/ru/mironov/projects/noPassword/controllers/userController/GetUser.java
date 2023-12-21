package ru.mironov.projects.noPassword.controllers.userController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.mironov.projects.noPassword.models.user.User;
import ru.mironov.projects.noPassword.security.UserDetailsImpl;
import ru.mironov.projects.noPassword.services.UserService;

@Service
public class GetUser {
    private final UserService userService;

    @Autowired
    public GetUser(UserService userService) {
        this.userService = userService;
    }

    public static User execute() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return userDetails.getUser();
    }
}
