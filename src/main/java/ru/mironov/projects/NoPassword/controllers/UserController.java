package ru.mironov.projects.NoPassword.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.mironov.projects.NoPassword.dto.UserDTO;
import ru.mironov.projects.NoPassword.models.user.User;
import ru.mironov.projects.NoPassword.security.UserDetailsImpl;
import ru.mironov.projects.NoPassword.services.UserService;

@Controller
@RequestMapping("/removable")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //User control
    @GetMapping("/user")
    public String userPage(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        //System.out.println(userDetails.getUser());
        model.addAttribute("user", userDetails.getUser());
        return "user/userPage";
    }

    //Admin control
    @GetMapping("/admin")
    public String adminPage(){
        return "user/admin_page";
    }

    //Other
    @PatchMapping("/password_change")
    public String changePassword(@ModelAttribute User user){
        //System.out.println(user);
        userService.changePassword(user);
        return "redirect:/data";
    }
}
