package com.wsei.healthcare.backend.infra.common.exception;

public abstract class AlreadyExistsException extends ApplicationException {
    public AlreadyExistsException(String message) {
        super(message);
    }

    public AlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
