package ru.mironov.projects.noPassword.services;

import ru.mironov.projects.noPassword.models.password.Password;

public interface PasswordService {
    void save(Password password);
}
