package ru.mironov.projects.noPassword.client.model;

import ru.mironov.projects.noPassword.models.password.ApplicationName;

public record TokenRequest(ApplicationName applicationName, String login, String password) {
}
