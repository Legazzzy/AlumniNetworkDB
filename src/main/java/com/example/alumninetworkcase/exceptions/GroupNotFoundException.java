package com.example.alumninetworkcase.exceptions;

public class GroupNotFoundException extends RuntimeException {

    public GroupNotFoundException(int id) {
        super("Group does not exist with ID: " + id);
    }

    public GroupNotFoundException(String message) {
        super(message);
    }

    public GroupNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public GroupNotFoundException(Throwable cause) {
        super(cause);
    }

    public GroupNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}