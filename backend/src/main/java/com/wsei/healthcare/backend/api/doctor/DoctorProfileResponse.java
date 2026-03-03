package com.wsei.healthcare.backend.api.doctor;

import java.util.List;

public record DoctorProfileResponse(
        Long id,

        String firstName,
        String lastName,
        String email,
        String phoneNumber,

        String specialization,
        String description

//        List<PatientShortInfo> patients
) {
}
