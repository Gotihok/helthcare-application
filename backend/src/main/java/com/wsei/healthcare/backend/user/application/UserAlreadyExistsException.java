package com.wsei.healthcare.backend.user.application;

import com.wsei.healthcare.backend.shared.exception.AlreadyExistsAppException;

public class UserAlreadyExistsException extends AlreadyExistsAppException {
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
}
