package com.wsei.healthcare.backend.shared.exception;

import org.springframework.http.HttpStatus;

public abstract class ApplicationException extends RuntimeException {
    public ApplicationException(String message) {
        super(message);
    }

    public abstract String getErrorCode();

    public abstract HttpStatus getHttpStatus();
}
