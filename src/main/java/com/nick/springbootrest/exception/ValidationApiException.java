package com.nick.springbootrest.exception;

public class ValidationApiException extends RuntimeException {
    public ValidationApiException(String message) {
        super(message);
    }
}