package com.wsei.healthcare.backend.patient.infra;

import com.wsei.healthcare.backend.patient.api.PatientProfileResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    //TODO: implement
    @GetMapping("/me")
    public ResponseEntity<PatientProfileResponse> getAuthenticatedPatient(Authentication auth) {
        return null;
    }

    //TODO: implement
    @PostMapping("/me")
    public ResponseEntity<PatientProfileResponse> updateAuthenticatedPatient(Authentication auth) {
        return null;
    }

    //TODO: implement
    @DeleteMapping("/me")
    public ResponseEntity<PatientProfileResponse> deleteAuthenticatedPatient(Authentication auth) {
        return null;
    }

    //TODO: implement
    @PostMapping("/me/doctor/{doctorId}")
    public ResponseEntity<PatientProfileResponse> setPersonalDoctor(
            Authentication auth,
            @PathVariable Long doctorId
    ) {
        return null;
    }

    //TODO: add all (paginated) prescriptions retrieval or maybe just return it in PatientProfileResponse
    //TODO: add visits management endpoints

    //TODO: implement
    @GetMapping("/{id}")
    public ResponseEntity<PatientProfileResponse> findPatientById(@PathVariable Long id) {
        return null;
    }

    //TODO: implement
    //TODO: add pagination
    //TODO: maybe change to admin only
    @GetMapping("")
    public ResponseEntity<List<PatientProfileResponse>> getAllPatients() {
        return null;
    }

    //TODO: add other search endpoints
}
