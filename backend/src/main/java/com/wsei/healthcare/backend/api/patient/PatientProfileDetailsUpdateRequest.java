package com.wsei.healthcare.backend.api.patient;

import java.time.LocalDate;

public record PatientProfileDetailsUpdateRequest(
        String firstName,
        String lastName,
//        String email,
        String phoneNumber,
        LocalDate dateOfBirth
) {
}
