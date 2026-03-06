package com.wsei.healthcare.backend.to_move.api.patient;

import java.time.LocalDate;

public record PatientProfileDetailsUpdateRequest(
        String firstName,
        String lastName,
//        String email,
        String phoneNumber,
        LocalDate dateOfBirth
) {
}
