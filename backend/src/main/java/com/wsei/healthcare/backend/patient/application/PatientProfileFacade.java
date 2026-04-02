package com.wsei.healthcare.backend.patient.application;

import com.wsei.healthcare.backend.patient.api.PatientProfileApi;
import com.wsei.healthcare.backend.patient.api.dto.PatientCreationRequest;
import com.wsei.healthcare.backend.patient.api.dto.PatientProfileResponse;
import com.wsei.healthcare.backend.patient.api.dto.PatientProfileUpdateRequest;
import com.wsei.healthcare.backend.patient.domain.Patient;
import com.wsei.healthcare.backend.patient.domain.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PatientProfileFacade implements PatientProfileApi {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private final PatientOrchestrationService orchestrator;

    @Override
    public PatientProfileResponse createPatientProfile(Long userId, PatientCreationRequest request) {
        if (patientRepository.existsByUserId(userId))
            throw new PatientAlreadyExistsException("Patient profile already registered for this user");

        Patient newPatient = patientMapper.toEntity(request);
        newPatient.setUserId(userId);

        Patient savedPatient = patientRepository.save(newPatient);
        return orchestrator.buildPatientProfile(savedPatient);
    }

    @Override
    public PatientProfileResponse updatePatientProfileById(Long userId, PatientProfileUpdateRequest request) {
        Patient oldPatientEntity = patientRepository.findByUserId(userId)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found by id"));
        Patient updatedPatient = patientMapper.toEntity(oldPatientEntity, request);

        Patient savedPatient = patientRepository.save(updatedPatient);
        return orchestrator.buildPatientProfile(savedPatient);
    }

    @Override
    public PatientProfileResponse getPatientProfileByUserId(Long userId) {
        Patient patient = patientRepository.findByUserId(userId)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found by id"));
        return orchestrator.buildPatientProfile(patient);
    }

    @Override
    public void deletePatientProfileByUserId(Long userId) {
        Patient patient = patientRepository.findByUserId(userId)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found by id"));
        patientRepository.deleteById(patient.getId());
    }
}
