package com.userservice.authorization.exception;

public class ClientAlreadyExistsException extends Exception{
    public ClientAlreadyExistsException(String message) {
        super(message);
    }
}
