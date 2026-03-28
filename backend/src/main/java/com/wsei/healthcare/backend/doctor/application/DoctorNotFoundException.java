package com.wsei.healthcare.backend.doctor.application;

import com.wsei.healthcare.backend.shared.exception.NotFoundAppException;

public class DoctorNotFoundException extends NotFoundAppException {
    public DoctorNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getErrorCode() {
        return "DOCTOR_NOT_FOUND";
    }
}
