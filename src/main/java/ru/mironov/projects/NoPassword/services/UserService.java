package ru.mironov.projects.NoPassword.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mironov.projects.NoPassword.dto.UserDTO;
import ru.mironov.projects.NoPassword.models.user.User;
import ru.mironov.projects.NoPassword.models.user.UserRole;
import ru.mironov.projects.NoPassword.repositories.UserRepository;
import ru.mironov.projects.NoPassword.util.UserNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }
    public User findOne(int id){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty())
            throw new UserNotFoundException(id);
        return user.get();
    }
//    @Transactional
//    public void save(UserDTO user){
//        userRepository.save(convertToUser(user));
//    }

    @Transactional
    public void register(User newUser){
        String encodedPassword = passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(encodedPassword);
        newUser.setRole(UserRole.ROLE_USER);
        System.out.println(newUser);
        userRepository.save(newUser);
    }
    @Transactional
    public void changePassword(User user){
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    public boolean isUserExists(String username){
        return userRepository.existsByUsername(username);
    }

    private User convertToUser(UserDTO userDTO){
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        enrichUser(user);
        return user;
    }

    private void enrichUser(User user) {
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        if(user.getRole() == null)
            user.setRole(UserRole.ROLE_USER);
    }
}
