package com.wsei.healthcare.backend.patient.api;

public interface PatientPublicApi {
    PatientProfileResponse createPatientProfile(Long userId, PatientCreationRequest request);

    PatientProfileResponse updatePatientProfileById(Long userId, PatientProfileUpdateRequest request);

    PatientProfileResponse getPatientProfileByUserId(Long userId);

    void deletePatientProfileByUserId(Long userId);

    PatientProfileResponse setPersonalDoctorByUserId(Long userId, Long doctorId);
}
