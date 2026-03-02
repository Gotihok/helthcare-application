package com.wsei.healthcare.backend.domain.user;

import com.wsei.healthcare.backend.infra.common.exception.AlreadyExistsException;
import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends AlreadyExistsException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }

    public UserAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public String getErrorCode() {
        return "USER_ALREADY_EXISTS";
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.CONFLICT;
    }
}
