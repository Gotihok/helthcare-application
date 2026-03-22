package com.wsei.healthcare.backend.patient.api;

import com.wsei.healthcare.backend.user.api.UserResponse;

public record PatientProfileResponse(
        UserResponse userProfile,
        PatientProfile patientProfile
) {}
