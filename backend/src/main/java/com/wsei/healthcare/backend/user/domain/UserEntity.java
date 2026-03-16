package com.wsei.healthcare.backend.user.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Data
@Accessors(chain = true)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ===== COMMON FIELDS =====

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

//    @Column(nullable = false)
    private String phoneNumber;

    // ===== PATIENT PROFILE (MANDATORY) =====

//    @Column(nullable = false)
    private LocalDate dateOfBirth;

//    /**
//     * Personal doctor (patient side of relation).
//     * Many patients can reference the same doctor.
//     */
//    @ManyToOne
//    @JoinColumn(name = "personal_doctor_id")
//    private UserEntity personalDoctor;
//
//    /**
//     * Upcoming visits for patient
//     */
//    @OneToMany(mappedBy = "patient")
//    private List<Visit> upcomingVisits = new ArrayList<>();
//
//    /**
//     * Active prescriptions for patient
//     */
//    @OneToMany(mappedBy = "patient")
//    private List<Prescription> activePrescriptions = new ArrayList<>();

    // ===== DOCTOR PROFILE (OPTIONAL) =====

//    /**
//     * If not null → this user is a doctor
//     */
//    @Column
//    private String specialization;
//
//    @Column(length = 2000)
//    private String description;
//
//    /**
//     * List of patients assigned to this doctor.
//     * Inverse side of personalDoctor relation.
//     */
//    @OneToMany(mappedBy = "personalDoctor")
//    private List<UserEntity> patients = new ArrayList<>();
}
