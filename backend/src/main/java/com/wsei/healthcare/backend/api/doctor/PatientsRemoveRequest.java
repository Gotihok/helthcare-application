package com.wsei.healthcare.backend.api.doctor;

import java.util.List;

public record PatientsRemoveRequest(
        List<Long> patientIds
) {
}
