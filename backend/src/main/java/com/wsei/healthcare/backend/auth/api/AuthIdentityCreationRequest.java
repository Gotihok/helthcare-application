package com.wsei.healthcare.backend.auth.api;

public record AuthIdentityCreationRequest(
    Long userId,
    String email,
    String password
) {}
