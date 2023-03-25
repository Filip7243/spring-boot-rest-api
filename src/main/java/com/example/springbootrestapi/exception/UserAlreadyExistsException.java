package com.example.springbootrestapi.exception;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String albumId) {
        super("User with albumId: " + albumId + " already exists!");
    }
}
