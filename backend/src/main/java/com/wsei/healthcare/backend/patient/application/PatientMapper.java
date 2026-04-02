package com.wsei.healthcare.backend.patient.application;

import com.wsei.healthcare.backend.doctor.application.DoctorMapper;
import com.wsei.healthcare.backend.patient.api.dto.PatientCreationRequest;
import com.wsei.healthcare.backend.patient.api.dto.PatientDetailsResponse;
import com.wsei.healthcare.backend.patient.api.dto.PatientProfileUpdateRequest;
import com.wsei.healthcare.backend.patient.domain.Patient;
import com.wsei.healthcare.backend.user.application.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

//TODO: create interface abstraction
@Component
@RequiredArgsConstructor
public class PatientMapper {

    private final UserMapper userMapper;
    private final DoctorMapper doctorMapper;

    //TODO: implement properly
    public PatientDetailsResponse toDto(Patient entity) {
        return new PatientDetailsResponse(
                entity.getId()
                );
    }

    //TODO: implement properly
    public Patient toEntity(PatientCreationRequest dto) {
        return new Patient();
    }

    public Patient toEntity(Patient baseEntity, PatientProfileUpdateRequest dto) {
        return baseEntity; //TODO: set all the fields from request to the entity
    }
}
