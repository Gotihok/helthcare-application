package com.wsei.healthcare.backend.user.application;

import com.wsei.healthcare.backend.shared.exception.NotFoundAppException;

public class UserNotFoundException extends NotFoundAppException {
    public UserNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getErrorCode() {
        return "USER_NOT_FOUND";
    }
}
