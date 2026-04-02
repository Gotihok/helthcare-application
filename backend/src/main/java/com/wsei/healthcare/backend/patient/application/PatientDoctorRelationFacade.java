package com.wsei.healthcare.backend.patient.application;

import com.wsei.healthcare.backend.patient.api.PatientDoctorRelationApi;
import com.wsei.healthcare.backend.patient.domain.PatientRepository;
import com.wsei.healthcare.backend.patient.domain.PatientRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientDoctorRelationFacade implements PatientDoctorRelationApi {

    private final PatientRepository patientRepository;
    private final PatientRequestRepository patientRequestRepository;

    @Override
    public void postPersonalDoctorAssignmentRequestByUserId(Long userId, Long doctorId) {
        //TODO: create assignment request
        //TODO: save the assignment request
    }

    @Override
    public void removePersonalDoctorByUserId(Long userId, Long doctorId) {
        //TODO: remove the personal doctor
        //TODO: throw the PersonalDoctorRemovedEvent
    }

    @Override
    public void acceptPersonalDoctorAssignmentRequestByUserId(Long userId, Long requestId) {
        //TODO: assign the personal doctor
        //TODO: find the assignment request or throw an exception
        //TODO: change the status of assignment request to APPROVED
    }

    @Override
    public void rejectPersonalDoctorAssignmentRequestByUserId(Long userId, Long requestId) {
        //TODO: find the assignment request or throw an exception
        //TODO: change the status of assignment request to REJECTED
    }
}
