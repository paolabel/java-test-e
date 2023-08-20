package com.e.javatest.exception;

/* Exception class used when an ID sent to update or delete an entity does not match any existing entities. */
public class InvalidIdException extends Exception {
    private static final long serialVersionUID = 2L;

    public InvalidIdException(String message) {
        super(message);
    }

    public InvalidIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
