package com.wsei.healthcare.backend.auth.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.Instant;

@Data
@Accessors(chain = true)
public class Jwt {
    private String jwt;
    private Instant expiresAt;
    private boolean loggedOut;
}
