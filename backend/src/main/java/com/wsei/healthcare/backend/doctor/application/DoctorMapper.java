package com.wsei.healthcare.backend.doctor.application;

import com.wsei.healthcare.backend.doctor.api.DoctorCreationRequest;
import com.wsei.healthcare.backend.doctor.api.DoctorDetailsResponse;
import com.wsei.healthcare.backend.doctor.api.DoctorProfileResponse;
import com.wsei.healthcare.backend.doctor.api.DoctorProfileUpdateRequest;
import com.wsei.healthcare.backend.doctor.domain.Doctor;
import com.wsei.healthcare.backend.user.application.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

//TODO: create interface abstraction
@Component
@RequiredArgsConstructor
public class DoctorMapper {

    private final UserMapper userMapper;

    //TODO: remove and add orchestrator
    public DoctorProfileResponse toProfileDto(Doctor entity) {
        return null;
    }

    //TODO: implement properly
    public DoctorDetailsResponse toDto(Doctor entity) {
        return new DoctorDetailsResponse(
                entity.getId()
        );
    }

    //TODO: implement properly
    public Doctor toEntity(DoctorCreationRequest dto) {
        return new Doctor();
    }

    public Doctor toEntity(Doctor baseEntity, DoctorProfileUpdateRequest dto) {
        return baseEntity; //TODO: set all the fields from request to the entity
    }
}