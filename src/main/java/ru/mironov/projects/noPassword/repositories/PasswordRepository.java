package ru.mironov.projects.noPassword.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mironov.projects.noPassword.models.password.Password;

public interface PasswordRepository extends JpaRepository<Password, Integer> {
}
