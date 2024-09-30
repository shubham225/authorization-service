package com.userservice.authorization.exception;

public class ScopeNotFoundException extends RuntimeException {
    public ScopeNotFoundException(String message) {
        super(message);
    }
}
