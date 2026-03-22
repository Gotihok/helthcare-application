package com.wsei.healthcare.backend.user.application;

import com.wsei.healthcare.backend.shared.exception.AlreadyExistsAppException;

public class UserAlreadyExistsException extends AlreadyExistsAppException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }

    @Override
    public String getErrorCode() {
        return "USER_ALREADY_EXISTS";
    }
}
