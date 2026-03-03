package com.wsei.healthcare.backend.application.patient;

import com.wsei.healthcare.backend.api.patient.PatientProfileDetailsUpdateRequest;
import com.wsei.healthcare.backend.api.patient.PatientProfileResponse;
import org.springframework.security.core.Authentication;

public interface PatientService {
    PatientProfileResponse getProfile(Authentication authentication);

    PatientProfileResponse updateProfile(PatientProfileDetailsUpdateRequest updateRequest, Authentication authentication);
}
