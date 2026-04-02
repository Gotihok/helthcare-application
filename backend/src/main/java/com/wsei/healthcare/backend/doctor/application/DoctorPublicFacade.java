package com.wsei.healthcare.backend.doctor.application;

import com.wsei.healthcare.backend.doctor.api.DoctorCreationRequest;
import com.wsei.healthcare.backend.doctor.api.DoctorProfileResponse;
import com.wsei.healthcare.backend.doctor.api.DoctorProfileUpdateRequest;
import com.wsei.healthcare.backend.doctor.api.DoctorPublicApi;
import com.wsei.healthcare.backend.doctor.domain.Doctor;
import com.wsei.healthcare.backend.doctor.domain.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorPublicFacade implements DoctorPublicApi {

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;
//    private final DoctorAssignmentManagementApi doctorAssignmentManagementApi;
    private final DoctorOrchestrationService orchestrator;

    @Override
    public DoctorProfileResponse createDoctorProfile(Long userId, DoctorCreationRequest request) {
        if (doctorRepository.existsByUserId(userId))
            throw new DoctorAlreadyExistsException("Patient profile already exists");

        Doctor newDoctor = doctorMapper.toEntity(request);
        newDoctor.setUserId(userId);

        Doctor savedDoctor = doctorRepository.save(newDoctor);
        return orchestrator.buildDoctorProfile(savedDoctor);
    }

    @Override
    public DoctorProfileResponse updateDoctorProfile(Long userId, DoctorProfileUpdateRequest request) {
        Doctor oldDoctorEntity = doctorRepository.findByUserId(userId)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found by id"));
        Doctor updatedDoctor = doctorMapper.toEntity(oldDoctorEntity, request);

        Doctor savedDoctor = doctorRepository.save(updatedDoctor);
        return orchestrator.buildDoctorProfile(savedDoctor);
    }

    @Override
    public DoctorProfileResponse getDoctorProfileByUserId(Long userId) {
        Doctor doctor = doctorRepository.findByUserId(userId)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found by id"));
        return orchestrator.buildDoctorProfile(doctor);
    }

    @Override
    public void deleteDoctorProfileByUserId(Long userId) {
        Doctor doctor = doctorRepository.findByUserId(userId)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found by id"));
        doctorRepository.deleteById(doctor.getId());
    }

    //TODO: make through the patient confirmation, not direct change
    @Override
    public DoctorProfileResponse addPatientForDoctor(Long userId, Long patientId) {
        Doctor doctor = doctorRepository.findByUserId(userId)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found by id"));
//        doctorAssignmentManagementApi.setPersonalDoctorByUserId(patientId, doctor.getId());
        return orchestrator.buildDoctorProfile(doctor);
    }

    //TODO: make through the patient confirmation, not direct change
    @Override
    public DoctorProfileResponse removePatientForDoctor(Long userId, Long patientId) {
        Doctor doctor = doctorRepository.findByUserId(userId)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found by id"));
//        doctorAssignmentManagementApi.removePersonalDoctorByUserId(patientId, doctor.getId());
        return orchestrator.buildDoctorProfile(doctor);
    }
}
