package com.wsei.healthcare.backend.user.application;

import com.wsei.healthcare.backend.shared.exception.ForbiddenOperationException;

public class EmailForbiddenOperationException extends ForbiddenOperationException {
    public EmailForbiddenOperationException(String message) {
        super(message);
    }

    @Override
    public String getErrorCode() {
        return "EMAIL_FORBIDDEN";
    }
}
