package com.wsei.healthcare.backend.patient.api;

public interface PatientDoctorRelationApi {

    void postPersonalDoctorAssignmentRequestByUserId(Long userId, Long doctorId);

    void removePersonalDoctorByUserId(Long userId, Long doctorId);

    void acceptPersonalDoctorAssignmentRequestByUserId(Long userId, Long requestId);

    void rejectPersonalDoctorAssignmentRequestByUserId(Long userId, Long requestId);
}
