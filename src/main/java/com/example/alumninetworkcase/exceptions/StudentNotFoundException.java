package com.example.alumninetworkcase.exceptions;

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(int id) {
        super("Student does not exist with ID: " + id);
    }

    public StudentNotFoundException(String message) {
        super(message);
    }

    public StudentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public StudentNotFoundException(Throwable cause) {
        super(cause);
    }

    public StudentNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
