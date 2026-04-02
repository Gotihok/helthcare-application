package com.wsei.healthcare.backend.patient.infra;

import com.wsei.healthcare.backend.auth.infra.security.UserPrincipal;
import com.wsei.healthcare.backend.patient.api.PatientDoctorRelationApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientDoctorRelationController {

    private final PatientDoctorRelationApi patientDoctorRelationApi;

    //TODO: maybe add some return value with info about posted assignment request
    @PostMapping("/me/doctor/{doctorId}")
    public ResponseEntity<Void> postPersonalDoctorAssignmentRequest(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable Long doctorId
    ) {
        patientDoctorRelationApi.postPersonalDoctorAssignmentRequestByUserId(
                userPrincipal.getUserId(),
                doctorId
        );
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/me/doctor/{doctorId}")
    public ResponseEntity<Void> removePersonalDoctor(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable Long doctorId
    ) {
        patientDoctorRelationApi.removePersonalDoctorByUserId(
                userPrincipal.getUserId(),
                doctorId
        );
        return ResponseEntity.ok().build();
    }

    //TODO: maybe add some return value with info about accepted assignment request
    @PostMapping("/me/doctor/request/{assignmentRequestId}/accept")
    public ResponseEntity<Void> acceptPersonalDoctor(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable Long assignmentRequestId
    ) {
        patientDoctorRelationApi.acceptPersonalDoctorAssignmentRequestByUserId(
                userPrincipal.getUserId(),
                assignmentRequestId
        );
        return ResponseEntity.ok().build();
    }

    //TODO: maybe add some return value with info about declined assignment request
    @PostMapping("/me/doctor/request/{assignmentRequestId}/reject")
    public ResponseEntity<Void> rejectPersonalDoctor(
            @AuthenticationPrincipal UserPrincipal userPrincipal,
            @PathVariable Long assignmentRequestId
    ) {
        patientDoctorRelationApi.rejectPersonalDoctorAssignmentRequestByUserId(
                userPrincipal.getUserId(),
                assignmentRequestId
        );
        return ResponseEntity.ok().build();
    }
}
