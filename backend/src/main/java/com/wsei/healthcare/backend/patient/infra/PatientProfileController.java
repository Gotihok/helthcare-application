package com.wsei.healthcare.backend.patient.infra;

import com.wsei.healthcare.backend.auth.infra.security.UserPrincipal;
import com.wsei.healthcare.backend.patient.api.PatientProfileApi;
import com.wsei.healthcare.backend.patient.api.dto.PatientCreationRequest;
import com.wsei.healthcare.backend.patient.api.dto.PatientProfileResponse;
import com.wsei.healthcare.backend.patient.api.dto.PatientProfileUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientProfileController {

    private final PatientProfileApi patientProfileApi;

    @PostMapping("/me/register")
    public ResponseEntity<PatientProfileResponse> createPatientProfile(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody PatientCreationRequest request
    ) {
        return ResponseEntity.ok(
                patientProfileApi.createPatientProfile(userPrincipal.getUserId(), request)
        );
    }

    @PostMapping("/me/update")
    public ResponseEntity<PatientProfileResponse> updateAuthenticatedPatientProfile(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody PatientProfileUpdateRequest request
    ) {
        return ResponseEntity.ok(
                patientProfileApi.updatePatientProfileById(userPrincipal.getUserId(), request)
        );
    }

    @GetMapping("/me")
    public ResponseEntity<PatientProfileResponse> getAuthenticatedPatientProfile(
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        return ResponseEntity.ok(
                patientProfileApi.getPatientProfileByUserId(userPrincipal.getUserId())
        );
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteAuthenticatedPatientProfile(
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        patientProfileApi.deletePatientProfileByUserId(userPrincipal.getUserId());
        return ResponseEntity.noContent().build();
    }
}
