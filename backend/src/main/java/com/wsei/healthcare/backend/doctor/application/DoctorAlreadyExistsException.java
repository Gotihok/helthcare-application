package com.wsei.healthcare.backend.doctor.application;

import com.wsei.healthcare.backend.shared.exception.AlreadyExistsAppException;

public class DoctorAlreadyExistsException extends AlreadyExistsAppException {
    public DoctorAlreadyExistsException(String message) {
        super(message);
    }

    @Override
    public String getErrorCode() {
        return "DOCTOR_ALREADY_EXISTS";
    }
}
