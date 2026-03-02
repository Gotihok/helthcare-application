package com.wsei.healthcare.backend.util.auth;

import com.wsei.healthcare.backend.api.auth.JwtResponse;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.Instant;

@Setter
@Accessors(chain = true)
public class JwtResponseBuilder implements AuthConstants {

    private String jwt;
    private Instant expiresAt;

    private JwtResponseBuilder() {}

    public static JwtResponseBuilder getStubDefault() {
        JwtResponseBuilder builder = new JwtResponseBuilder();
        builder.jwt = JWT_STUB;
        builder.expiresAt = JWT_EXPIRATION_STUB;
        return builder;
    }

    public JwtResponse build() {
        return new JwtResponse(jwt, expiresAt);
    }
}
