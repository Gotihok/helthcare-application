package com.wsei.healthcare.backend.patient.application;

import com.wsei.healthcare.backend.shared.exception.AlreadyExistsAppException;

public class PatientAlreadyExistsException extends AlreadyExistsAppException {
    public PatientAlreadyExistsException(String message) {
        super(message);
    }

    @Override
    public String getErrorCode() {
        return "PATIENT_ALREADY_EXISTS";
    }
}
