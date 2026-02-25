package com.wsei.healthcare.backend.infra.common.exception;

import org.springframework.http.HttpStatus;

public abstract class ApplicationException extends RuntimeException {
    public ApplicationException(String message) {
        super(message);
    }

    public ApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract String getErrorCode();

    public abstract HttpStatus getHttpStatus();
}
