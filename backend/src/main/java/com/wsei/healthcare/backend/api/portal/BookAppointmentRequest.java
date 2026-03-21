package com.wsei.healthcare.backend.api.portal;

import jakarta.validation.constraints.NotBlank;

public record BookAppointmentRequest(
        @NotBlank String date,
        @NotBlank String time,
        @NotBlank String reason
) {
}
