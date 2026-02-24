package com.wsei.healthcare.backend.user.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "User login request payload")
public record UserLoginRequest(
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
