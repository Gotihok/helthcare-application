package com.wsei.healthcare.backend.api.patient;

public record DoctorShortInfo(
        Long id,
        String firstName,
        String lastName,
        String specialization
) {
}
