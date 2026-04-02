package com.wsei.healthcare.backend.patient.api;

public interface OutgoingDoctorRequestApi {

    void sendRequest(Long userId, Long doctorId);

}
