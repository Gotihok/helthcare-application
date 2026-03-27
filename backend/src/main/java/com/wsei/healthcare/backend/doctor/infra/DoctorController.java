package com.wsei.healthcare.backend.doctor.infra;

import com.wsei.healthcare.backend.auth.infra.security.UserPrincipal;
import com.wsei.healthcare.backend.doctor.api.DoctorApi;
import com.wsei.healthcare.backend.doctor.api.DoctorCreationRequest;
import com.wsei.healthcare.backend.doctor.api.DoctorProfileResponse;
import com.wsei.healthcare.backend.doctor.api.DoctorProfileUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorApi doctorApi;

    //TODO: add role guard or other security mechanism to prevent unauthorized users from registering as doctors
    @PostMapping("/me/register")
    public ResponseEntity<DoctorProfileResponse> createDoctorProfile(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody DoctorCreationRequest request
    ) {
        return ResponseEntity.ok(
                doctorApi.createDoctorProfile(userPrincipal.getUserId(), request)
        );
    }

    @PostMapping("/me")
    public ResponseEntity<DoctorProfileResponse> updateAuthenticatedDoctorProfile(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody DoctorProfileUpdateRequest request
    ) {
        return ResponseEntity.ok(
                doctorApi.updateDoctorProfile(userPrincipal.getUserId(), request)
        );
    }

    @GetMapping("/me")
    public ResponseEntity<DoctorProfileResponse> getAuthenticatedDoctorProfile(
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        return ResponseEntity.ok(
                doctorApi.getDoctorProfileByUserId(userPrincipal.getUserId())
        );
    }

    @DeleteMapping("/me")
    public ResponseEntity<DoctorProfileResponse> deleteAuthenticatedDoctorProfile(
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        doctorApi.deleteDoctorProfileByUserId(userPrincipal.getUserId());
        return ResponseEntity.noContent().build();
    }

    ///TODO: possibly change to return profile
    @PostMapping("/me/patients/{patientId}")
    public ResponseEntity<Void> addPatient(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable Long patientId
    ) {
        doctorApi.addPatientForDoctor(userPrincipal.getUserId(), patientId);
        return ResponseEntity.ok().build();
    }

    ///TODO: possibly change to return profile
    @DeleteMapping("/me/patients/{patientId}")
    public ResponseEntity<Void> removePatient(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable Long patientId
    ) {
        doctorApi.removePatientForDoctor(userPrincipal.getUserId(), patientId);
        return ResponseEntity.ok().build();
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
