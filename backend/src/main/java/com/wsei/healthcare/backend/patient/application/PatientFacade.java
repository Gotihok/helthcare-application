package com.wsei.healthcare.backend.patient.application;

import com.wsei.healthcare.backend.doctor.domain.Doctor;
import com.wsei.healthcare.backend.patient.api.PatientApi;
import com.wsei.healthcare.backend.patient.api.PatientProfileResponse;
import com.wsei.healthcare.backend.patient.api.PatientProfileUpdateRequest;
import com.wsei.healthcare.backend.patient.domain.Patient;
import com.wsei.healthcare.backend.patient.domain.PatientRepository;
import com.wsei.healthcare.backend.patient.api.PatientCreationRequest;
import com.wsei.healthcare.backend.user.domain.AppUser;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientFacade implements PatientApi {

    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    @Override
    public PatientProfileResponse createPatientProfile(Long userId, PatientCreationRequest request) {
        if (patientRepository.existsByUserId(userId))
            throw new PatientAlreadyExistsException("Patient profile already exists");

        Patient newPatient = patientMapper.toEntity(request);
        newPatient.setUser(new AppUser().setId(userId));

        Patient savedPatient = patientRepository.save(newPatient);
        return patientMapper.toProfileDto(savedPatient);
    }

    @Override
    public PatientProfileResponse updatePatientProfileById(Long userId, PatientProfileUpdateRequest request) {
        Patient oldPatientEntity = patientRepository.findByUserId(userId)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found by id"));
        Patient updatedPatient = patientMapper.toEntity(oldPatientEntity, request);

        Patient savedPatient = patientRepository.save(updatedPatient);
        return patientMapper.toProfileDto(savedPatient);
    }

    @Override
    public PatientProfileResponse getPatientProfileByUserId(Long userId) {
        Patient patient = patientRepository.findByUserId(userId)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found by id"));
        return patientMapper.toProfileDto(patient);
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

        patient.setPersonalDoctor(new Doctor().setId(doctorId));
        Patient savedPatient = patientRepository.save(patient);

        return patientMapper.toProfileDto(savedPatient);
    }

    @Override
    public void setPersonalDoctorByPatientId(Long patientId, Long doctorId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found by id"));

        patient.setPersonalDoctor(new Doctor().setId(doctorId));
        patientRepository.save(patient);
    }

    @Override
    public void removePersonalDoctorByPatientId(Long patientId, Long doctorId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found by id"));

        patient.setPersonalDoctor(null);
        patientRepository.save(patient);
    }
}
