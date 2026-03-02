package com.wsei.healthcare.backend.util.auth;

import com.wsei.healthcare.backend.api.auth.JwtResponse;
import com.wsei.healthcare.backend.infra.security.Jwt;

public final class JwtResponseFactory implements AuthConstants {

    private JwtResponseFactory() {}

    public static JwtResponse from(Jwt jwt) {
        return new JwtResponse(jwt.getJwt(), jwt.getExpiresAt());
    }
}
