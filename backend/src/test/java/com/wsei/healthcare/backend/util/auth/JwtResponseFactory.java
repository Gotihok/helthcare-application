package com.wsei.healthcare.backend.util.auth;

import com.wsei.healthcare.backend.auth.api.JwtResponse;
import com.wsei.healthcare.backend.auth.domain.Jwt;

public final class JwtResponseFactory implements AuthConstants {

    private JwtResponseFactory() {}

    public static JwtResponse from(Jwt jwt) {
        return new JwtResponse(jwt.getJwt(), jwt.getExpiresAt());
    }
}
