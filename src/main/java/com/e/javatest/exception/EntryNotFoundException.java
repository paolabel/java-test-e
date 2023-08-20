package com.e.javatest.exception;

/* Exception class used when an entity is not found for a single entry lookup. */
public class EntryNotFoundException extends Exception {
    private static final long serialVersionUID = 4L;

    public EntryNotFoundException(String message) {
        super(message);
    }

    public EntryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
