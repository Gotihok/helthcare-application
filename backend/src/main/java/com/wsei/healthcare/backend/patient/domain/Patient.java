package com.wsei.healthcare.backend.patient.domain;

import com.wsei.healthcare.backend.doctor.domain.Doctor;
import com.wsei.healthcare.backend.user.domain.AppUser;
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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private AppUser user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor personalDoctor;

    //TODO: add visits info with relations
    //TODO: add active prescriptions relation (updatable with each retrieval)
    //TODO: add patient specific info
}
