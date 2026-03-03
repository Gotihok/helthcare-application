package com.wsei.healthcare.backend.api.patient;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface PatientApi {

    ResponseEntity<PatientProfileResponse> getOwnProfile(Authentication authentication);

    ResponseEntity<PatientProfileResponse> updateProfileDetails(
            PatientProfileDetailsUpdateRequest updateRequest,
            Authentication authentication
    );

    ResponseEntity<PatientProfileResponse> setPersonalDoctor(
            PersonalDoctorSetRequest setRequest,
            Authentication authentication
    );
}
