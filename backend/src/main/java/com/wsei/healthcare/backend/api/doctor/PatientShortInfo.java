package com.wsei.healthcare.backend.api.doctor;

import java.time.LocalDate;

public record PatientShortInfo(
        Long id,
        String firstName,
        String lastName,
        String email,
        LocalDate dateOfBirth
) {
}
