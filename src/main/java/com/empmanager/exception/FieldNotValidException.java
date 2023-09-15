package com.empmanager.exception;

public class FieldNotValidException extends RuntimeException{

    public FieldNotValidException(String message, Exception ex) {
        super(message, ex);
    }

    public FieldNotValidException(String message) {
        super(message);
    }
}
