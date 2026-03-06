package com.wsei.healthcare.backend.shared.exception;

public abstract class AlreadyExistsException extends ApplicationException {
    public AlreadyExistsException(String message) {
        super(message);
    }

    public AlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

}
