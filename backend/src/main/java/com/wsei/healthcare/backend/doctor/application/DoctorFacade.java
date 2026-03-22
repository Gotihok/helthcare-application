package com.wsei.healthcare.backend.doctor.application;

import com.wsei.healthcare.backend.doctor.api.DoctorApi;
import com.wsei.healthcare.backend.doctor.api.DoctorCreationRequest;
import com.wsei.healthcare.backend.doctor.api.DoctorProfileResponse;
import com.wsei.healthcare.backend.doctor.api.DoctorProfileUpdateRequest;
import com.wsei.healthcare.backend.doctor.domain.Doctor;
import com.wsei.healthcare.backend.doctor.domain.DoctorRepository;
import com.wsei.healthcare.backend.patient.api.PatientApi;
import com.wsei.healthcare.backend.user.domain.AppUser;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorFacade implements DoctorApi {

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;
    private final PatientApi patientApi;

    @Override
    public DoctorProfileResponse createDoctorProfile(Long userId, DoctorCreationRequest request) {
        if (doctorRepository.existsByUserId(userId))
            throw new DoctorAlreadyExistsException("Patient profile already exists");

        Doctor newDoctor = doctorMapper.toEntity(request);
        newDoctor.setUser(new AppUser().setId(userId));

        Doctor savedDoctor = doctorRepository.save(newDoctor);
        return doctorMapper.toProfileDto(savedDoctor);
    }

    @Override
    public DoctorProfileResponse updateDoctorProfile(Long userId, DoctorProfileUpdateRequest request) {
        Doctor oldDoctorEntity = doctorRepository.findByUserId(userId)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found by id"));
        Doctor updatedDoctor = doctorMapper.toEntity(oldDoctorEntity, request);

        Doctor savedDoctor = doctorRepository.save(updatedDoctor);
        return doctorMapper.toProfileDto(savedDoctor);
    }

    @Override
    public DoctorProfileResponse getDoctorProfileByUserId(Long userId) {
        Doctor doctor = doctorRepository.findByUserId(userId)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found by id"));
        return doctorMapper.toProfileDto(doctor);
    }

    ///TODO: implement
    @Override
    public void deleteDoctorProfileByUserId(Long userId) {
        throw new NotImplementedException("Doctor deletion is not implemented yet");
    }

    @Override
    public void addPatientForDoctor(Long userId, Long patientId) {
        Doctor doctor = doctorRepository.findByUserId(userId)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found by id"));
        patientApi.setPersonalDoctorByPatientId(patientId, doctor.getId());
    }

    @Override
    public void removePatientForDoctor(Long userId, Long patientId) {
        Doctor doctor = doctorRepository.findByUserId(userId)
                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found by id"));
        patientApi.removePersonalDoctorByPatientId(patientId, doctor.getId());
    }
}
