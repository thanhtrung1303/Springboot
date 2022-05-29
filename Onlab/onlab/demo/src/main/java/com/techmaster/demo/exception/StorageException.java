package com.techmaster.demo.exception;

public class StorageException extends RuntimeException {
    private static final long serialVersionUID = 628888888888L;

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
