package com.wsei.healthcare.backend.doctor.domain;

import com.wsei.healthcare.backend.patient.domain.Patient;
import com.wsei.healthcare.backend.user.domain.AppUser;
import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashSet;
import java.util.Set;

@Data
@Accessors(chain = true)
@Entity
@Table(name = "doctor_profiles")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private AppUser user;

    @OneToMany(mappedBy = "personalDoctor", fetch = FetchType.LAZY)
    private Set<Patient> patients = new HashSet<>();

    //TODO: add visits info with relations
    //TODO: add doctor specific info
}