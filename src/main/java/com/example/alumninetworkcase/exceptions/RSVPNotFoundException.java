package com.example.alumninetworkcase.exceptions;

public class RSVPNotFoundException extends RuntimeException {
    public RSVPNotFoundException(int id) {
        super("RSVP does not exist with ID: " + id);
    }

    public RSVPNotFoundException(String message) {
        super(message);
    }

    public RSVPNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public RSVPNotFoundException(Throwable cause) {
        super(cause);
    }

    public RSVPNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
