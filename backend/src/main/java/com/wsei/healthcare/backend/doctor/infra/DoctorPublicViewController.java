package com.wsei.healthcare.backend.doctor.infra;

import com.wsei.healthcare.backend.doctor.api.dto.DoctorProfileResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorPublicViewController {

    //TODO: add all (paginated) prescriptions retrieval or maybe just return it in DoctorProfileResponse
    //TODO: add visits management endpoints

    //TODO: implement
    @GetMapping("/{id}")
    public ResponseEntity<DoctorProfileResponse> findDoctorById(@PathVariable Long id) {
        return null;
    }

    //TODO: implement
    //TODO: add pagination
    @GetMapping("")
    public ResponseEntity<List<DoctorProfileResponse>> getAllDoctors() {
        return null;
    }

    //TODO: add other search endpoints
}
