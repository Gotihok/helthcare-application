package com.wsei.healthcare.backend.api.patient;

import java.time.LocalDateTime;

public record VisitShortInfo(
        Long id,
        LocalDateTime visitDate,
        String reason,
        String status
) {
}
