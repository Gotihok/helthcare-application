package com.wsei.healthcare.backend.doctor.api;

public interface DoctorPublicApi {
    DoctorProfileResponse createDoctorProfile(Long userId, DoctorCreationRequest request);

    DoctorProfileResponse updateDoctorProfile(Long userId, DoctorProfileUpdateRequest request);

    DoctorProfileResponse getDoctorProfileByUserId(Long userId);

    void deleteDoctorProfileByUserId(Long userId);

    //TODO: make to be returning profile response or move to inner
    DoctorProfileResponse addPatientForDoctor(Long userId, Long patientId);

    DoctorProfileResponse removePatientForDoctor(Long userId, Long patientId);
}
