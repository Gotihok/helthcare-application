package com.wsei.healthcare.backend.doctor.application;

import com.wsei.healthcare.backend.doctor.api.DoctorProfileResponse;
import com.wsei.healthcare.backend.doctor.domain.Doctor;

public interface DoctorOrchestrationService {
    DoctorProfileResponse buildDoctorProfile(Doctor doctor);
}
