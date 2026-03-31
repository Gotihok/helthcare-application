package com.wsei.healthcare.backend.patient.api;

import com.wsei.healthcare.backend.doctor.api.DoctorProfileResponse;
import com.wsei.healthcare.backend.user.api.UserResponse;

public record PatientProfileResponse(
        UserResponse user,
        PatientDetailsResponse patient,
        DoctorProfileResponse personalDoctor
) {}
