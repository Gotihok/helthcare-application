package com.wsei.healthcare.backend.doctor.infra;

import com.wsei.healthcare.backend.auth.infra.security.UserPrincipal;
import com.wsei.healthcare.backend.doctor.api.DoctorPatientRelationApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
public class DoctorPatientRelationController {

    private final DoctorPatientRelationApi doctorPatientRelationApi;

    @PostMapping("/me/patients/{patientId}")
    public ResponseEntity<Void> postPatientAdditionRequest(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable Long patientId
    ) {
        doctorPatientRelationApi.addPatientForDoctor(userPrincipal.getUserId(), patientId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/me/patients/{patientId}")
    public ResponseEntity<Void> removePatient(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable Long patientId
    ) {
        doctorPatientRelationApi.removePatientForDoctor(userPrincipal.getUserId(), patientId);
        return ResponseEntity.ok().build();
    }


}
