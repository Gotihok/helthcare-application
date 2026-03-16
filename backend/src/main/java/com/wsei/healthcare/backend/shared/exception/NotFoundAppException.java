package com.wsei.healthcare.backend.shared.exception;

import org.springframework.http.HttpStatus;

public abstract class NotFoundAppException extends ApplicationException {
    public NotFoundAppException(String message) {
        super(message);
    }

    public NotFoundAppException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
