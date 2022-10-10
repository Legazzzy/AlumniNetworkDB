package com.example.alumninetworkcase.exceptions;

public class AlumniEventNotFoundException extends RuntimeException{

    public AlumniEventNotFoundException(int id) {
        super("Event does not exist with ID: " + id);
    }

    public AlumniEventNotFoundException(String message) {
        super(message);
    }

    public AlumniEventNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlumniEventNotFoundException(Throwable cause) {
        super(cause);
    }

    public AlumniEventNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
