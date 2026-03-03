package com.wsei.healthcare.backend.infra.security;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.Instant;

@Data
@Accessors(chain = true)
public class Jwt {
    String jwt;
    Instant expiresAt;
}
