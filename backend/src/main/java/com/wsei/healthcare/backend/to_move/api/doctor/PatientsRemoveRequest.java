package com.wsei.healthcare.backend.to_move.api.doctor;

import java.util.List;

public record PatientsRemoveRequest(
        List<Long> patientIds
) {
}
