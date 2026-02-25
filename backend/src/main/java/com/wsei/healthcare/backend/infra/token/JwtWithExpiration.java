package com.wsei.healthcare.backend.infra.token;

import java.time.Instant;

//TODO: rework to a domain object if needed
public record JwtWithExpiration (
        String jwt,
        Instant expiresAt
) {
}
