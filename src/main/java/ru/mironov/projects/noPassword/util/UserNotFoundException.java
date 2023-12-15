package ru.mironov.projects.noPassword.util;

public class UserNotFoundException extends RuntimeException{
    private final int id;

    public UserNotFoundException(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
