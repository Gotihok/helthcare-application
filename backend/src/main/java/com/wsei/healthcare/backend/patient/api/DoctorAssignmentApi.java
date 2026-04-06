package com.wsei.healthcare.backend.patient.api;

public interface DoctorAssignmentApi {

    void requestAssignment(Long actorId, Long doctorId);

    void acceptRequest(Long actorId, Long requestId);

    void rejectRequest(Long actorId, Long requestId);

    void removeAssignment(Long actorId);
}
