package com.wsei.healthcare.backend.patient.domain;

import com.wsei.healthcare.backend.shared.enums.RequestStatus;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "doctor_assignment_requests")
public class DoctorAssignmentRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long patientId;
    private Long doctorId;
    private RequestStatus status;
    private Instant createdAt;
    private Instant processedAt;
}
