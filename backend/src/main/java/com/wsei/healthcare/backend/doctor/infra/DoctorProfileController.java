package com.wsei.healthcare.backend.doctor.infra;

import com.wsei.healthcare.backend.auth.infra.security.UserPrincipal;
import com.wsei.healthcare.backend.doctor.api.DoctorProfileApi;
import com.wsei.healthcare.backend.doctor.api.dto.DoctorCreationRequest;
import com.wsei.healthcare.backend.doctor.api.dto.DoctorProfileResponse;
import com.wsei.healthcare.backend.doctor.api.dto.DoctorProfileUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorProfileController {

    private final DoctorProfileApi doctorProfileApi;

    //TODO: add role guard or other security mechanism to prevent unauthorized users from registering as doctors
    @PostMapping("/me/register")
    public ResponseEntity<DoctorProfileResponse> createDoctorProfile(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody DoctorCreationRequest request
    ) {
        return ResponseEntity.ok(
                doctorProfileApi.createDoctorProfile(userPrincipal.getUserId(), request)
        );
    }

    @PostMapping("/me/update")
    public ResponseEntity<DoctorProfileResponse> updateAuthenticatedDoctorProfile(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @RequestBody DoctorProfileUpdateRequest request
    ) {
        return ResponseEntity.ok(
                doctorProfileApi.updateDoctorProfile(userPrincipal.getUserId(), request)
        );
    }

    @GetMapping("/me")
    public ResponseEntity<DoctorProfileResponse> getAuthenticatedDoctorProfile(
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        return ResponseEntity.ok(
                doctorProfileApi.getDoctorProfileByUserId(userPrincipal.getUserId())
        );
    }

    @DeleteMapping("/me")
    public ResponseEntity<DoctorProfileResponse> deleteAuthenticatedDoctorProfile(
            @AuthenticationPrincipal UserPrincipal userPrincipal
    ) {
        doctorProfileApi.deleteDoctorProfileByUserId(userPrincipal.getUserId());
        return ResponseEntity.noContent().build();
    }
}
