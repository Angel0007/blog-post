package com.example.muskan.exception;

public class UserNotFoundException extends RuntimeException {
    private String message;
    public UserNotFoundException(String message)
    {
        this.message=message;
    }
}