package com.wsei.healthcare.backend.api.portal;

public record AppointmentResponse(
        Long id,
        String date,
        String time,
        String doctorName,
        String patientName,
        String status
) {
}
