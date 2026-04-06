package com.wsei.healthcare.backend.doctor.application;

import com.wsei.healthcare.backend.doctor.api.DoctorPatientRelationApi;
import org.springframework.stereotype.Service;

@Service
public class DoctorPatientRelationFacade implements DoctorPatientRelationApi {
    @Override
    public void addPatientForDoctor(Long userId, Long patientId) {
        //
    }

    @Override
    public void removePatientForDoctor(Long userId, Long patientId) {
        //
    }
}
