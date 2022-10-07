package com.example.alumninetworkcase.exceptions;


public class AlumniGroupNotFoundException extends RuntimeException {

    public AlumniGroupNotFoundException(int id) {
        super("Group does not exist with ID: " + id);
    }

    public AlumniGroupNotFoundException(String message) {
        super(message);
    }

    public AlumniGroupNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlumniGroupNotFoundException(Throwable cause) {
        super(cause);
    }

    public AlumniGroupNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}