package com.wsei.healthcare.backend.patient.application;

import com.wsei.healthcare.backend.patient.api.PatientInnerApi;
import com.wsei.healthcare.backend.patient.domain.Patient;
import com.wsei.healthcare.backend.patient.domain.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientInnerFacade implements PatientInnerApi {

    private final PatientRepository patientRepository;

    @Override
    public void setPersonalDoctorByPatientId(Long patientId, Long doctorId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found by id"));

        patient.setPersonalDoctorId(doctorId);
        patientRepository.save(patient);
    }

    @Override
    public void removePersonalDoctorByPatientId(Long patientId, Long doctorId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found by id"));

        patient.setPersonalDoctorId(null);
        patientRepository.save(patient);
    }
}
