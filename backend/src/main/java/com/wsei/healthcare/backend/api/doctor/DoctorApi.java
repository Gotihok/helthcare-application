package com.wsei.healthcare.backend.api.doctor;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface DoctorApi {

    ResponseEntity<DoctorProfileResponse> getOwnProfile(Authentication authentication);

    ResponseEntity<DoctorProfileResponse> updateProfileDetails(
            DoctorProfileDetailsUpdateRequest updateRequest,
            Authentication authentication
    );

    ResponseEntity<DoctorProfileResponse> removePatients(
            PatientsRemoveRequest removeRequest,
            Authentication authentication
    );
}
