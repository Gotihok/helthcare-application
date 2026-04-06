package com.wsei.healthcare.backend.doctor.api;

public interface DoctorPatientRelationApi {
    void addPatientForDoctor(Long userId, Long patientId);

    void removePatientForDoctor(Long userId, Long patientId);
}
