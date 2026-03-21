package com.wsei.healthcare.backend.api.portal;

public record PrescriptionResponse(
        Long id,
        String code,
        String issuedAt,
        String doctorName,
        String medicine,
        String dosage
) {
}
