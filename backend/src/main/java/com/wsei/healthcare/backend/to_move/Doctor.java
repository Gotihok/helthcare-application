package com.wsei.healthcare.backend.to_move;

import java.util.List;

public class Doctor {
    private Long id;
    private ProfileInfo patientProfile;
    private String specialization;
    private String description;

    private List<Patient> patients;
    //    private List<Visit> upcomingVisits;
}
