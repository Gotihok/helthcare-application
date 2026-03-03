package com.wsei.healthcare.backend.api.patient;

import java.time.LocalDate;

public record PrescriptionShortInfo(
        Long id,
        String medicationName,
        LocalDate issuedDate,
        LocalDate validUntil,
        boolean active,
        String pdfDownloadUrl
) {
}
