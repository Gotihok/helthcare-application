package com.wsei.healthcare.backend.doctor.application;

import com.wsei.healthcare.backend.doctor.api.DoctorInnerApi;
import com.wsei.healthcare.backend.doctor.domain.DoctorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoctorInnerFacade implements DoctorInnerApi {

    private final DoctorRepository doctorRepository;

    @Override
    public boolean existsById(Long doctorId) {
        return doctorRepository.existsById(doctorId);
    }
}
