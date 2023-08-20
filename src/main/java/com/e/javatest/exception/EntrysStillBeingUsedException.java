package com.e.javatest.exception;

/* Exception class used when an entity cannot be deleted due to it still being used by another entity. */
public class EntrysStillBeingUsedException extends Exception {
    private static final long serialVersionUID = 3L;

    public EntrysStillBeingUsedException(String message) {
        super(message);
    }

    public EntrysStillBeingUsedException(String message, Throwable cause) {
        super(message, cause);
    }
}
