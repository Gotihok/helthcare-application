package com.wsei.healthcare.backend.patient.application;

import com.wsei.healthcare.backend.patient.api.PatientCreationRequest;
import com.wsei.healthcare.backend.patient.api.PatientProfileResponse;
import com.wsei.healthcare.backend.patient.api.PatientProfileUpdateRequest;
import com.wsei.healthcare.backend.patient.api.PatientPublicApi;
import com.wsei.healthcare.backend.patient.domain.Patient;
import com.wsei.healthcare.backend.patient.domain.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PatientPublicFacade implements PatientPublicApi {

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

    //TODO: implement or remove completely
    @Override
    public void deletePatientProfileByUserId(Long userId) {
        throw new NotImplementedException("Patient deletion is not implemented yet");
    }

    //TODO: extract boilerplate
    @Override
    public PatientProfileResponse setPersonalDoctorByUserId(Long userId, Long doctorId) {
        Patient patient = patientRepository.findByUserId(userId)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found by id"));

        patient.setPersonalDoctorId(doctorId);
        Patient savedPatient = patientRepository.save(patient);

        return orchestrator.buildPatientProfile(savedPatient);
    }

//    @Override
//    public void setPersonalDoctorByPatientId(Long patientId, Long doctorId) {
//        Patient patient = patientRepository.findById(patientId)
//                .orElseThrow(() -> new PatientNotFoundException("Patient not found by id"));
//
//        patient.setPersonalDoctorId(doctorId);
//        patientRepository.save(patient);
//    }
//
//    @Override
//    public void removePersonalDoctorByPatientId(Long patientId, Long doctorId) {
//        Patient patient = patientRepository.findById(patientId)
//                .orElseThrow(() -> new PatientNotFoundException("Patient not found by id"));
//
//        patient.setPersonalDoctorId(null);
//        patientRepository.save(patient);
//    }
}
