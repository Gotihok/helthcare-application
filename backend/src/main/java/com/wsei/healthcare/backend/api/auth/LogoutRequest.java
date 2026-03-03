package com.wsei.healthcare.backend.api.auth;

import io.swagger.v3.oas.annotations.media.Schema;

//TODO: test
@Schema(description = "Logout request payload")
public record LogoutRequest(
        @Schema(
                description = "JWT string with Bearer prefix",
                example = "Bearer eyJhbGciOiJIUzI1NiIsI.nR5cCI6IkpXVCJ9..."
        )
        String jwt
) {
}
