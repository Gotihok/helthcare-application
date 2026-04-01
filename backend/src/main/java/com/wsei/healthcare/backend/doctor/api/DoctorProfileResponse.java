package com.wsei.healthcare.backend.doctor.api;

import com.wsei.healthcare.backend.patient.api.PatientProfileResponse;
import com.wsei.healthcare.backend.user.api.UserResponse;

import java.util.List;

public record DoctorProfileResponse(
        UserResponse user,
        DoctorDetailsResponse doctor,
        List<PatientProfileResponse> patients
) {
}
