package com.wsei.healthcare.backend.doctor.api;

public interface DoctorApi {
    DoctorProfileResponse createDoctorProfile(Long userId, DoctorCreationRequest request);

    DoctorProfileResponse updateDoctorProfile(Long userId, DoctorProfileUpdateRequest request);

    DoctorProfileResponse getDoctorProfileByUserId(Long userId);

    void deleteDoctorProfileByUserId(Long userId);

    void addPatientForDoctor(Long userId, Long patientId);

    void removePatientForDoctor(Long userId, Long patientId);
}
