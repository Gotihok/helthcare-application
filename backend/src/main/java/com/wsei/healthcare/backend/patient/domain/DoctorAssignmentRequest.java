package com.wsei.healthcare.backend.patient.domain;

import com.wsei.healthcare.backend.shared.enums.RequestStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.Instant;

//TODO: consider relation with the Patient
@Data
@Accessors(chain = true)
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
