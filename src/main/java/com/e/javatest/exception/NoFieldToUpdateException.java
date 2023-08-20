package com.e.javatest.exception;

/* Exception class used when no fields are given (the request body is empty) for an update request. */
public class NoFieldToUpdateException extends Exception {
    private static final long serialVersionUID = 5L;

    public NoFieldToUpdateException(String message) {
        super(message);
    }

    public NoFieldToUpdateException(String message, Throwable cause) {
        super(message, cause);
    }
}
