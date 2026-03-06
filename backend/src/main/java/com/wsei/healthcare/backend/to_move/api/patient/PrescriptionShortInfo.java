package com.wsei.healthcare.backend.to_move.api.patient;

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
