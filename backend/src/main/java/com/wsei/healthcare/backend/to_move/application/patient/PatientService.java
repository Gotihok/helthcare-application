package com.wsei.healthcare.backend.to_move.application.patient;

import com.wsei.healthcare.backend.to_move.api.patient.PatientProfileDetailsUpdateRequest;
import com.wsei.healthcare.backend.to_move.api.patient.PatientProfileResponse;
import org.springframework.security.core.Authentication;

public interface PatientService {
    PatientProfileResponse getProfile(Authentication authentication);

    PatientProfileResponse updateProfile(PatientProfileDetailsUpdateRequest updateRequest, Authentication authentication);
}
