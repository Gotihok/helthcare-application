package com.wsei.healthcare.backend.patient.application;

import com.wsei.healthcare.backend.shared.exception.InvalidReferenceException;

public class InvalidPersonalDoctorReferenceException extends InvalidReferenceException {
    public InvalidPersonalDoctorReferenceException(String string) {
        super(string);
    }

    @Override
    public String getErrorCode() {
        return "INVALID_PERSONAL_DOCTOR_REFERENCE";
    }
}
