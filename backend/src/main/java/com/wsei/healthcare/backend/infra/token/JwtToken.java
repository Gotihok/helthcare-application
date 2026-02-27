package com.wsei.healthcare.backend.infra.token;

import java.time.Instant;

//TODO: rework to a domain object if needed
public record JwtToken(
        String jwt,
        Instant expiresAt
) {
}
