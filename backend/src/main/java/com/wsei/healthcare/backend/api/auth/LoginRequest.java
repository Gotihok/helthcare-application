package com.wsei.healthcare.backend.api.auth;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Login request payload")
public record LoginRequest(
        @Schema(
                description = "User email address",
                example = "jimmy.neutron@example.com"
        )
        String email,

        @Schema(
                description = "User password",
                example = "P@ssw0rd123",
                accessMode = Schema.AccessMode.WRITE_ONLY
        )
        String password
) {
}
