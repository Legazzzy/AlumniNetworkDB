package com.example.alumninetworkcase.exceptions;

public class TopicNotFoundException extends RuntimeException {

    public TopicNotFoundException(int id) {
        super("Topic does not exist with ID: " + id);
    }

    public TopicNotFoundException(String message) {
        super(message);
    }

    public TopicNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public TopicNotFoundException(Throwable cause) {
        super(cause);
    }

    public TopicNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}