package com.wsei.healthcare.backend.to_move.api.patient;

import java.time.LocalDateTime;

public record VisitShortInfo(
        Long id,
        LocalDateTime visitDate,
        String reason,
        String status
) {
}
