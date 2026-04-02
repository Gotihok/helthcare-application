package com.wsei.healthcare.backend.shared.exception;

import org.springframework.http.HttpStatus;

public abstract class InvalidReferenceException extends ApplicationException {
    public InvalidReferenceException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
