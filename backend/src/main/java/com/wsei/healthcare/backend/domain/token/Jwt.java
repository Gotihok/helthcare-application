package com.wsei.healthcare.backend.domain.token;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.Instant;

@Data
@Accessors(chain = true)
public class Jwt {
    String jwt;
    Instant expiresAt;
}
