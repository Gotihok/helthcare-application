package com.wsei.healthcare.backend.doctor.infra;

import com.wsei.healthcare.backend.auth.infra.security.UserPrincipal;
import com.wsei.healthcare.backend.doctor.api.DoctorCreationRequest;
import com.wsei.healthcare.backend.doctor.api.DoctorProfileResponse;
import com.wsei.healthcare.backend.doctor.api.DoctorProfileUpdateRequest;
import com.wsei.healthcare.backend.doctor.api.DoctorPublicApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorPublicApi doctorPublicApi;

    //TODO: add role guard or other security mechanism to prevent unauthorized users from registering as doctors
    @PostMapping("/me/register")
    public ResponseEntity<DoctorProfileResponse> createDoctorProfile(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody DoctorCreationRequest request
    ) {
        return ResponseEntity.ok(
                doctorPublicApi.createDoctorProfile(userPrincipal.getUserId(), request)
        );
    }

    @PostMapping("/me/update")
    public ResponseEntity<DoctorProfileResponse> updateAuthenticatedDoctorProfile(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody DoctorProfileUpdateRequest request
    ) {
        return ResponseEntity.ok(
                doctorPublicApi.updateDoctorProfile(userPrincipal.getUserId(), request)
        );
    }

    @GetMapping("/me")
    public ResponseEntity<DoctorProfileResponse> getAuthenticatedDoctorProfile(
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        return ResponseEntity.ok(
                doctorPublicApi.getDoctorProfileByUserId(userPrincipal.getUserId())
        );
    }

    @DeleteMapping("/me")
    public ResponseEntity<DoctorProfileResponse> deleteAuthenticatedDoctorProfile(
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        doctorPublicApi.deleteDoctorProfileByUserId(userPrincipal.getUserId());
        return ResponseEntity.noContent().build();
    }

    ///TODO: possibly change to return profile
    @PostMapping("/me/patients/{patientId}")
    public ResponseEntity<DoctorProfileResponse> addPatient(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable Long patientId
    ) {
        return ResponseEntity.ok(
                doctorPublicApi.addPatientForDoctor(userPrincipal.getUserId(), patientId)
        );
    }

    ///TODO: possibly change to return profile
    @DeleteMapping("/me/patients/{patientId}")
    public ResponseEntity<DoctorProfileResponse> removePatient(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable Long patientId
    ) {
        return ResponseEntity.ok(
                doctorPublicApi.removePatientForDoctor(userPrincipal.getUserId(), patientId)
        );
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
    @GetMapping("")
    public ResponseEntity<List<DoctorProfileResponse>> getAllDoctors() {
        return null;
    }

    //TODO: add other search endpoints
}
