package com.wsei.healthcare.backend.to_move.api.doctor;

public record DoctorProfileDetailsUpdateRequest(
        String firstName,
        String lastName,
        String email,
        String phoneNumber,

        String specialization,
        String description
) {
}
