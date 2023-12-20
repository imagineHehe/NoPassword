package ru.mironov.projects.noPassword.services;

import ru.mironov.projects.noPassword.dto.UserDTO;
import ru.mironov.projects.noPassword.models.user.User;


import java.util.List;

public interface UserService {
    List<User> findAll();

    User findOne(int id);

    void save(UserDTO user);
    boolean isUserExists(String username, String email);

}
