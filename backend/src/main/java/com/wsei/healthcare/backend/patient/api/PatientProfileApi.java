package com.wsei.healthcare.backend.patient.api;

import com.wsei.healthcare.backend.patient.api.dto.PatientCreationRequest;
import com.wsei.healthcare.backend.patient.api.dto.PatientProfileResponse;
import com.wsei.healthcare.backend.patient.api.dto.PatientProfileUpdateRequest;

public interface PatientProfileApi {
    PatientProfileResponse createPatientProfile(Long userId, PatientCreationRequest request);

    PatientProfileResponse updatePatientProfileById(Long userId, PatientProfileUpdateRequest request);

    PatientProfileResponse getPatientProfileByUserId(Long userId);

    void deletePatientProfileByUserId(Long userId);
}
