package com.wsei.healthcare.backend.to_move;

import com.wsei.healthcare.backend.to_move.api.patient.PatientApi;
import com.wsei.healthcare.backend.to_move.api.patient.PatientProfileDetailsUpdateRequest;
import com.wsei.healthcare.backend.to_move.api.patient.PatientProfileResponse;
import com.wsei.healthcare.backend.to_move.api.patient.PersonalDoctorSetRequest;
import com.wsei.healthcare.backend.to_move.application.patient.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patient")
@RequiredArgsConstructor
public class PatientController implements PatientApi {

    private final PatientService patientService;

    @Override
    @GetMapping("/me/profile")
    public ResponseEntity<PatientProfileResponse> getOwnProfile(Authentication authentication) {
        return ResponseEntity.ok(
                patientService.getProfile(authentication)
        );
    }

    @Override
    @PostMapping("/me/profile")
    public ResponseEntity<PatientProfileResponse> updateProfileDetails(
            @RequestBody
            PatientProfileDetailsUpdateRequest updateRequest,
            Authentication authentication
    ) {
        return ResponseEntity.ok(
                patientService.updateProfile(
                        updateRequest,
                        authentication
                )
        );
    }

    @Override
    @PostMapping("/me/doctor")
    public ResponseEntity<PatientProfileResponse> setPersonalDoctor(
            @RequestBody
            PersonalDoctorSetRequest setRequest,
            Authentication authentication
    ) {
        return null;
    }
}
