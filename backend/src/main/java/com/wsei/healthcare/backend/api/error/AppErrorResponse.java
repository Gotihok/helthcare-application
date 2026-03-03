package com.wsei.healthcare.backend.api.error;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

//TODO: document
@Schema(description = "Error response")
public record AppErrorResponse(

        @Schema(
                description = "JWT expiration timestamp (UTC, ISO-8601)",
                example = "2026-02-23T14:37:00Z"
        )
        //TODO: maybe initialize as now in default constructor
        Instant timestamp,
        int httpStatus,
        String errorCode,
        String message,
        String path
) {
}
