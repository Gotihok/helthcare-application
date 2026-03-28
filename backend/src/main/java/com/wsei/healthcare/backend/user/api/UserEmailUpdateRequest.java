package com.wsei.healthcare.backend.user.api;

public record UserEmailUpdateRequest(
        String oldEmail,
        String newEmail
) {
}
