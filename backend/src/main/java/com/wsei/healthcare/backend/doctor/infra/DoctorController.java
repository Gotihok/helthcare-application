package com.wsei.healthcare.backend.doctor.infra;

import com.wsei.healthcare.backend.doctor.api.DoctorProfileResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    //TODO: implement
    @GetMapping("/me")
    public ResponseEntity<DoctorProfileResponse> getAuthenticatedDoctor(Authentication auth) {
        return null;
    }

    //TODO: implement
    @PostMapping("/me")
    public ResponseEntity<DoctorProfileResponse> updateAuthenticatedDoctor(Authentication auth) {
        return null;
    }

    //TODO: implement
    @DeleteMapping("/me")
    public ResponseEntity<DoctorProfileResponse> deleteAuthenticatedDoctor(Authentication auth) {
        return null;
    }

    //TODO: implement
    @PostMapping("/me/patients/{patientId}")
    public ResponseEntity<Void> addPatient(
            Authentication auth,
            @PathVariable Long patientId
    ) {
        return null;
    }

    //TODO: implement
    @DeleteMapping("/me/patients/{patientId}")
    public ResponseEntity<Void> removePatient(
            Authentication auth,
            @PathVariable Long patientId
    ) {
        return null;
    }

    //TODO: add all (paginated) prescriptions retrieval or maybe just return it in DoctorProfileResponse
    //TODO: add visits management endpoints

    //TODO: implement
    @GetMapping("/{id}")
    public ResponseEntity<DoctorProfileResponse> findDoctorById(@PathVariable Long id) {
        return null;
    }

    //TODO: implement
    //TODO: add pagination
    //TODO: maybe change to admin only
    @GetMapping("")
    public ResponseEntity<List<DoctorProfileResponse>> getAllDoctors() {
        return null;
    }

    //TODO: add other search endpoints
}
