package com.e.javatest.exception;

/* Exception class used when an entity is not found by the given ID. */
public class InvalidIdForUpdateException extends Exception {
    private static final long serialVersionUID = 2L;

    public InvalidIdForUpdateException(String message) {
        super(message);
    }

    public InvalidIdForUpdateException(String message, Throwable cause) {
        super(message, cause);
    }
}
