package com.wsei.healthcare.backend.doctor.application;

import com.wsei.healthcare.backend.doctor.api.DoctorDetailsResponse;
import com.wsei.healthcare.backend.doctor.api.DoctorProfileResponse;
import com.wsei.healthcare.backend.doctor.domain.Doctor;
import com.wsei.healthcare.backend.patient.api.PatientProfileResponse;
import com.wsei.healthcare.backend.patient.api.PatientPublicApi;
import com.wsei.healthcare.backend.user.api.UserApi;
import com.wsei.healthcare.backend.user.api.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorOrchestrationServiceImpl implements DoctorOrchestrationService {

    private final DoctorMapper doctorMapper;
    //TODO: add patient private api with dto retrievals
    private final UserApi userApi;

    @Override
    public DoctorProfileResponse buildDoctorProfile(Doctor doctor) {
        DoctorDetailsResponse doctorDto = doctorMapper.toDto(doctor);
        UserResponse userDto = userApi.getById(doctor.getUserId());

        //TODO: change to patients retrieval
        List<PatientProfileResponse> patientsDtoList = new ArrayList<>();

        return new DoctorProfileResponse(
                userDto,
                doctorDto,
                patientsDtoList
        );
    }
}
