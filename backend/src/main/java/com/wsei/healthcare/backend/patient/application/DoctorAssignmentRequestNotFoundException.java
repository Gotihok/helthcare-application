package com.wsei.healthcare.backend.patient.application;

import com.wsei.healthcare.backend.shared.exception.NotFoundAppException;

public class DoctorAssignmentRequestNotFoundException extends NotFoundAppException {
    public DoctorAssignmentRequestNotFoundException(String message) {
        super(message);
    }

    @Override
    public String getErrorCode() {
        return "DOCTOR_ASSIGNMENT_REQUEST_NOT_FOUND";
    }
}
