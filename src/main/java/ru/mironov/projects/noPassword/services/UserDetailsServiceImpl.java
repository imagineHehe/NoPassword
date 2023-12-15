package ru.mironov.projects.noPassword.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mironov.projects.noPassword.models.user.User;
import ru.mironov.projects.noPassword.repositories.UserRepository;
import ru.mironov.projects.noPassword.security.UserDetailsImpl;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;
    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> person = userRepository.findByUsername(username);

        if (person.isEmpty()){
            throw new UsernameNotFoundException("User not found");
        }
        return new UserDetailsImpl(person.get());

    }
}
