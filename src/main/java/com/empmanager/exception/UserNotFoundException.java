package com.empmanager.exception;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String message, Exception ex) {
        super(message, ex);
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
