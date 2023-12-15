package ru.mironov.projects.NoPassword.util;

public class UserNotFoundException extends RuntimeException{
    private final int id;

    public UserNotFoundException(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
