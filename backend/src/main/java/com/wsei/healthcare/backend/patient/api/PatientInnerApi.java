package com.wsei.healthcare.backend.patient.api;

public interface PatientInnerApi {

    void setPersonalDoctorByPatientId(Long patientId, Long doctorId);

    void removePersonalDoctorByPatientId(Long patientId, Long doctorId);
}
