package com.wsei.healthcare.backend.patient.application;

import com.wsei.healthcare.backend.doctor.api.DoctorInnerApi;
import com.wsei.healthcare.backend.patient.api.PatientCreationRequest;
import com.wsei.healthcare.backend.patient.api.PatientProfileResponse;
import com.wsei.healthcare.backend.patient.api.PatientProfileUpdateRequest;
import com.wsei.healthcare.backend.patient.api.PatientPublicApi;
import com.wsei.healthcare.backend.patient.domain.Patient;
import com.wsei.healthcare.backend.patient.domain.PatientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PatientPublicFacade implements PatientPublicApi {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;
    private final PatientOrchestrationService orchestrator;
    private final DoctorInnerApi doctorInnerApi;

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

    @Override
    public PatientProfileResponse setPersonalDoctorByUserId(Long userId, Long doctorId) {
        //TODO: make doctorId validation
        Patient patient = patientRepository.findByUserId(userId)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found by id"));

        if (!doctorInnerApi.existsById(doctorId))
            throw new InvalidPersonalDoctorReferenceException("Doctor not found by id: " + doctorId);
        patient.setPersonalDoctorId(doctorId);
        Patient savedPatient = patientRepository.save(patient);

        return orchestrator.buildPatientProfile(savedPatient);
    }
}
