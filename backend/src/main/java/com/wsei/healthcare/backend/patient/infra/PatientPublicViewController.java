package com.wsei.healthcare.backend.patient.infra;

import com.wsei.healthcare.backend.patient.api.dto.PatientProfileResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientPublicViewController {

    //TODO: add all (paginated) prescriptions retrieval or maybe just return it in PatientProfileResponse
    //TODO: add visits management endpoints

    //TODO: implement
    //TODO: change to public dto (other users profiles)
    @GetMapping("/{id}")
    public ResponseEntity<PatientProfileResponse> findPatientById(@PathVariable Long id) {
        return null;
    }

    //TODO: implement
    //TODO: add pagination
    //TODO: maybe change to admin/personalDoctor only
    //TODO: change to public dto (other users profiles)
    @GetMapping("")
    public ResponseEntity<List<PatientProfileResponse>> getAllPatients() {
        return null;
    }

    //TODO: add other search endpoints
}
