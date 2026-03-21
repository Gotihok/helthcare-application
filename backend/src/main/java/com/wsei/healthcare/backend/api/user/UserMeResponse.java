package com.wsei.healthcare.backend.api.user;

public record UserMeResponse(
        String email,
        String role
) {
}
