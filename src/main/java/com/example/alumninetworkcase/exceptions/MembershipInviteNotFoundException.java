package com.example.alumninetworkcase.exceptions;

public class MembershipInviteNotFoundException extends RuntimeException{

    public MembershipInviteNotFoundException(int id) {
        super("Membership does not exist with ID: " + id);
    }

    public MembershipInviteNotFoundException(String message) {
        super(message);
    }

    public MembershipInviteNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MembershipInviteNotFoundException(Throwable cause) {
        super(cause);
    }

    public MembershipInviteNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
