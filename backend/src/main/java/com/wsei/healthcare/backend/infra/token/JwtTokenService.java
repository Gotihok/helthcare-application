package com.wsei.healthcare.backend.infra.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;

@Slf4j
@Service
public class JwtTokenService {
    private final SecretKey key;
    private final long jwtExpirationSeconds;
    @Getter
    private final String type;

    public JwtTokenService(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration.seconds}") long jwtExpirationSeconds,
            @Value("${jwt.prefix}") String type
            ) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.jwtExpirationSeconds = jwtExpirationSeconds;
        this.type = type;
    }

    public JwtWithExpiration generateToken(@NotBlank String name) {
        Instant now = Instant.now();
        Instant expiration = now.plusSeconds(jwtExpirationSeconds);

        String jwt = Jwts.builder()
                .subject(name)
                .issuedAt(Date.from(now))
                .expiration(Date.from(expiration))
                .signWith(key)
                .compact();

        return new JwtWithExpiration(jwt, expiration);
    }

    public boolean isValid(@NotBlank String token) {
        try {
            parseClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String resolveToken(String header) {
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }

    public String getUsername(@NotBlank String token) {
        return parseClaims(token).getSubject();
    }

    public Instant getExpiration(@NotBlank String token) {
        return parseClaims(token).getExpiration().toInstant();
    }

    private Claims parseClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
