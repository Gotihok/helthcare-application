package com.wsei.healthcare.backend.patient.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRequestRepository extends JpaRepository<DoctorAssignmentRequest, Long> {
}
