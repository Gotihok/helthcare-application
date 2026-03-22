package com.wsei.healthcare.backend.patient.application;

import com.wsei.healthcare.backend.patient.api.PatientDetailsResponse;
import com.wsei.healthcare.backend.patient.api.PatientProfileResponse;
import com.wsei.healthcare.backend.patient.api.PatientProfileUpdateRequest;
import com.wsei.healthcare.backend.patient.domain.Patient;
import com.wsei.healthcare.backend.patient.infra.PatientCreationRequest;
import com.wsei.healthcare.backend.user.application.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

//TODO: create interface abstraction
@Component
@RequiredArgsConstructor
public class PatientMapper {

    private final UserMapper userMapper;

    public PatientProfileResponse toProfileDto(Patient entity) {
        return new PatientProfileResponse(
                userMapper.toDto(entity.getUser()),
                toDto(entity)
        );
    }

    //TODO: implement properly
    public PatientDetailsResponse toDto(Patient entity) {
        return new PatientDetailsResponse();
    }

    //TODO: implement properly
    public Patient toEntity(PatientCreationRequest dto) {
        return new Patient();
    }

    public Patient toEntity(Patient baseEntity, PatientProfileUpdateRequest dto) {
        return baseEntity; //TODO: set all the fields from request to the entity
    }
}
