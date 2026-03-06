package com.wsei.healthcare.backend.to_move.application.patient;

import com.wsei.healthcare.backend.to_move.api.patient.PatientProfileDetailsUpdateRequest;
import com.wsei.healthcare.backend.to_move.api.patient.PatientProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    @Override
    public PatientProfileResponse getProfile(Authentication authentication) {
        return null;
    }

    @Override
    public PatientProfileResponse updateProfile(
            PatientProfileDetailsUpdateRequest updateRequest,
            Authentication authentication
    ) {
        return null;
    }
}
