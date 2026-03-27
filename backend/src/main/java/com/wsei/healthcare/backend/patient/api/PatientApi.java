package com.wsei.healthcare.backend.patient.api;

public interface PatientApi {
    PatientProfileResponse createPatientProfile(Long userId, PatientCreationRequest request);

    PatientProfileResponse updatePatientProfileById(Long userId, PatientProfileUpdateRequest request);

    PatientProfileResponse getPatientProfileByUserId(Long userId);

    void deletePatientProfileByUserId(Long userId);

    PatientProfileResponse setPersonalDoctorByUserId(Long userId, Long doctorId);

    void setPersonalDoctorByPatientId(Long patientId, Long doctorId);

    void removePersonalDoctorByPatientId(Long patientId, Long doctorId);
}
