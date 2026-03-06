package com.wsei.healthcare.backend.to_move.api.patient;

public record DoctorShortInfo(
        Long id,
        String firstName,
        String lastName,
        String specialization
) {
}
