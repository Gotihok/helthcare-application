package com.wsei.healthcare.backend.user.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "User logout request payload")
public record UserLogoutRequest(
        @Schema(
                description = "JWT string with Bearer prefix",
                example = "Bearer eyJhbGciOiJIUzI1NiIsI.nR5cCI6IkpXVCJ9..."
        )
        String jwt
) {
}
