package ru.mironov.projects.noPassword.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mironov.projects.noPassword.models.password.Password;
import ru.mironov.projects.noPassword.repositories.PasswordRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PasswordServiceJPA implements PasswordService{
    private final PasswordRepository passwordRepository;
    @Override
    @Transactional
    public void save(Password password) {
        passwordRepository.save(password);
    }
}
