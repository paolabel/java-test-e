package com.e.javatest.exception;

/* Exception class used when an already existing ID or name is used to register a new entity. */
public class DuplicateEntryException extends Exception {
    private static final long serialVersionUID = 1L;

    public DuplicateEntryException(String message) {
        super(message);
    }

    public DuplicateEntryException(String message, Throwable cause) {
        super(message, cause);
    }
}
