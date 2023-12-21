package ru.mironov.projects.noPassword.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mironov.projects.noPassword.dto.UserDTO;
import ru.mironov.projects.noPassword.models.user.User;
import ru.mironov.projects.noPassword.models.user.UserRole;
import ru.mironov.projects.noPassword.repositories.UserRepository;
import ru.mironov.projects.noPassword.util.UserNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserServiceJPA implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceJPA(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findOne(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty())
            throw new UserNotFoundException(id);
        return user.get();
    }
    @Transactional
    public void save(User user){
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }


    public boolean isUserExists(String username, String email) {
        return userRepository.existsByUsernameOrEmail(username, email);
    }


//    private User convertToUser(UserDTO userDTO) {
//        User user = new User();
//        user.setUsername(userDTO.getUsername());
//        user.setPassword(userDTO.getPassword());
//        enrichUser(user);
//        return user;
//    }
//
//    private void enrichUser(User user) {
//        String encodedPassword = passwordEncoder.encode(user.getPassword());
//        user.setPassword(encodedPassword);
//    }
}
