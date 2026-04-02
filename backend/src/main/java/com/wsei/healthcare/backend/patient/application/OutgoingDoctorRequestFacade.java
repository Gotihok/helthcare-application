package com.wsei.healthcare.backend.patient.application;

import com.wsei.healthcare.backend.patient.api.OutgoingDoctorRequestApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//TODO: check for transactions needed
//TODO: check for doctor existence in assignments
@Service
@RequiredArgsConstructor
public class OutgoingDoctorRequestFacade implements OutgoingDoctorRequestApi {

    @Override
    public void sendRequest(Long userId, Long doctorId) {
        //TODO: implement
    }
}
