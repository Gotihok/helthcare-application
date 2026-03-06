package com.wsei.healthcare.backend.auth.domain;

public record AuthCredentials(
        String email,
        String password
) {
}
