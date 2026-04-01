package com.wsei.healthcare.backend.patient.application;

import com.wsei.healthcare.backend.doctor.api.DoctorProfileResponse;
import com.wsei.healthcare.backend.doctor.api.DoctorApi;
import com.wsei.healthcare.backend.doctor.application.DoctorNotFoundException;
import com.wsei.healthcare.backend.patient.api.PatientDetailsResponse;
import com.wsei.healthcare.backend.patient.api.PatientProfileResponse;
import com.wsei.healthcare.backend.patient.domain.Patient;
import com.wsei.healthcare.backend.user.api.UserApi;
import com.wsei.healthcare.backend.user.api.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientOrchestrationServiceImpl implements PatientOrchestrationService {

    private final PatientMapper patientMapper;
    private final UserApi userApi;
    private final DoctorApi doctorApi;

    //TODO: change to be retrieving info by domains (all needed users and doctors)
    //      and to be composing the answer from those retrieved entities.
    //      To achieve that it's better to split apis to public/in-app
    //      and the responses should be thin, domain-specific until they reach orchestrator
    @Override
    public PatientProfileResponse buildPatientProfile(Patient patient) {
        PatientDetailsResponse patientDto = patientMapper.toDto(patient);
        UserResponse userDto = userApi.getById(patient.getUserId());

        //TODO: change to thin personalDoctor public view
        //TODO: make helper method to create optional responses with NotFoundEx processing
        DoctorProfileResponse doctorDto;
        try {
            doctorDto = doctorApi.getDoctorProfileByUserId(patient.getUserId());
        } catch (DoctorNotFoundException e) {
            doctorDto = null;
        }

        return new PatientProfileResponse(
                userDto,
                patientDto,
                doctorDto
        );

        //map patient to dto

        //collect id of users
        //retrieving users details

        //collect id of profiles
        //retrieving users profiles
    }
}
