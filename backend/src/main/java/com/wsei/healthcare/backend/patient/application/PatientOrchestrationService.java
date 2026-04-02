package com.wsei.healthcare.backend.patient.application;

import com.wsei.healthcare.backend.patient.api.dto.PatientProfileResponse;
import com.wsei.healthcare.backend.patient.domain.Patient;

public interface PatientOrchestrationService {

    PatientProfileResponse buildPatientProfile(Patient patient);
}
