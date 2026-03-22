package com.wsei.healthcare.backend.doctor.api;

import com.wsei.healthcare.backend.user.api.UserResponse;

public record DoctorProfileResponse(
        UserResponse userProfile,
        DoctorProfile doctorProfile
) {
}
