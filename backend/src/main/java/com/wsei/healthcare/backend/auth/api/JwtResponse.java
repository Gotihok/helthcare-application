package com.wsei.healthcare.backend.auth.api;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

@Schema(description = "JWT response")
public record JwtResponse(

        @Schema(
                description = "JWT string with Bearer prefix",
                example = "eyJhbGciOiJIUzI1NiIsI.nR5cCI6IkpXVCJ9..."
        )
        String jwt,

        @Schema(
                description = "JWT expiration timestamp (UTC, ISO-8601)",
                example = "2026-02-23T14:37:00Z"
        )
        Instant expiresAt
) {}
