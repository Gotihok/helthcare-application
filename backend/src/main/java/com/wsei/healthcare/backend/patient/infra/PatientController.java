package com.wsei.healthcare.backend.patient.infra;

import com.wsei.healthcare.backend.auth.infra.security.UserPrincipal;
import com.wsei.healthcare.backend.patient.api.PatientCreationRequest;
import com.wsei.healthcare.backend.patient.api.PatientProfileResponse;
import com.wsei.healthcare.backend.patient.api.PatientProfileUpdateRequest;
import com.wsei.healthcare.backend.patient.api.PatientPublicApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientPublicApi patientPublicApi;

    @PostMapping("/me/register")
    public ResponseEntity<PatientProfileResponse> createPatientProfile(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody PatientCreationRequest request
    ) {
        return ResponseEntity.ok(
                patientPublicApi.createPatientProfile(userPrincipal.getUserId(), request)
        );
    }

    @PostMapping("/me/update")
    public ResponseEntity<PatientProfileResponse> updateAuthenticatedPatientProfile(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody PatientProfileUpdateRequest request
    ) {
        return ResponseEntity.ok(
                patientPublicApi.updatePatientProfileById(userPrincipal.getUserId(), request)
        );
    }

    @GetMapping("/me")
    public ResponseEntity<PatientProfileResponse> getAuthenticatedPatientProfile(
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        return ResponseEntity.ok(
                patientPublicApi.getPatientProfileByUserId(userPrincipal.getUserId())
        );
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteAuthenticatedPatientProfile(
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        patientPublicApi.deletePatientProfileByUserId(userPrincipal.getUserId());
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/me/doctor/{doctorId}")
    public ResponseEntity<PatientProfileResponse> setPersonalDoctor(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable Long doctorId
    ) {
        return ResponseEntity.ok(
                patientPublicApi.setPersonalDoctorByUserId(userPrincipal.getUserId(), doctorId)
        );
    }

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
    //TODO: maybe change to admin/doctor only
    //TODO: change to public dto (other users profiles)
    @GetMapping("")
    public ResponseEntity<List<PatientProfileResponse>> getAllPatients() {
        return null;
    }

    //TODO: add other search endpoints
}
