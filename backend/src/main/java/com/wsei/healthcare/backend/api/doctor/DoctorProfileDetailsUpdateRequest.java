package com.wsei.healthcare.backend.api.doctor;

public record DoctorProfileDetailsUpdateRequest(
        String firstName,
        String lastName,
        String email,
        String phoneNumber,

        String specialization,
        String description
) {
}
