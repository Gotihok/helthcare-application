package com.wsei.healthcare.backend.domain.user;

import java.time.LocalDate;
import java.util.List;

public class AppUser {
    private Long id;

    // Common
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private LocalDate dateOfBirth;

    // Patient
    private Doctor personalDoctor;
//    private List<Visit> upcomingVisits;
//    private List<Prescription> activePrescriptions;

    // Doctor
    private String specialization;
    private String description;
    private List<Patient> patients;
}
