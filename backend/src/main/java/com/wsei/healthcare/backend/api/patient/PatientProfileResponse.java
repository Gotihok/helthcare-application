package com.wsei.healthcare.backend.api.patient;

import java.time.LocalDate;
import java.util.List;

public record PatientProfileResponse(
        Long id,

        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        LocalDate dateOfBirth

//        DoctorShortInfo personalDoctor,
//        List<VisitShortInfo> upcomingVisits,
//        List<PrescriptionShortInfo> activePrescriptions
) {
}
