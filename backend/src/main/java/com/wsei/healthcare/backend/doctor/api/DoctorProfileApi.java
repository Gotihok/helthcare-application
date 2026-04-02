package com.wsei.healthcare.backend.doctor.api;

import com.wsei.healthcare.backend.doctor.api.dto.DoctorCreationRequest;
import com.wsei.healthcare.backend.doctor.api.dto.DoctorProfileResponse;
import com.wsei.healthcare.backend.doctor.api.dto.DoctorProfileUpdateRequest;

public interface DoctorProfileApi {
    DoctorProfileResponse createDoctorProfile(Long userId, DoctorCreationRequest request);

    DoctorProfileResponse updateDoctorProfile(Long userId, DoctorProfileUpdateRequest request);

    DoctorProfileResponse getDoctorProfileByUserId(Long userId);

    void deleteDoctorProfileByUserId(Long userId);

    DoctorProfileResponse addPatientForDoctor(Long userId, Long patientId);

    DoctorProfileResponse removePatientForDoctor(Long userId, Long patientId);
}
