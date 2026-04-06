package com.wsei.healthcare.backend.doctor.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "doctor_profiles")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    //TODO: consider if allowed to be here and split to domain/entity if domain only
//    private Set<Long> patientIds = new HashSet<>();

    //TODO: add visits info with relations
    //TODO: add personalDoctor specific info
}