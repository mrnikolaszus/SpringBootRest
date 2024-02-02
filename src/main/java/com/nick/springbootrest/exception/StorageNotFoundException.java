package com.nick.springbootrest.exception;

public class StorageNotFoundException  extends RuntimeException {
    public StorageNotFoundException(String message) {
        super(message);
    }
}
