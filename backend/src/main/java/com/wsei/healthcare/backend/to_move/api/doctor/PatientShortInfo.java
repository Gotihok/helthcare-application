package com.wsei.healthcare.backend.to_move.api.doctor;

import java.time.LocalDate;

public record PatientShortInfo(
        Long id,
        String firstName,
        String lastName,
        String email,
        LocalDate dateOfBirth
) {
}
