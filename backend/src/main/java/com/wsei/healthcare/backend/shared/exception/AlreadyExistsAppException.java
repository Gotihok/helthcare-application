package com.wsei.healthcare.backend.shared.exception;

import org.springframework.http.HttpStatus;

public abstract class AlreadyExistsAppException extends ApplicationException {
    public AlreadyExistsAppException(String message) {
        super(message);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.CONFLICT;
    }
}
