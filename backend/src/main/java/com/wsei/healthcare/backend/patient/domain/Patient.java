package com.wsei.healthcare.backend.patient.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "patient_profiles")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long personalDoctorId;

    //TODO: add visits info with relations
    //TODO: add active prescriptions relation (updatable with each retrieval)
    //TODO: add patient specific info
}
