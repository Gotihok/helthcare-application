package com.wsei.healthcare.backend.patient.application;

import com.wsei.healthcare.backend.shared.exception.NotFoundAppException;

public class PatientNotFoundException extends NotFoundAppException {
    public PatientNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getErrorCode() {
        return "PATIENT_NOT_FOUND";
    }
}
