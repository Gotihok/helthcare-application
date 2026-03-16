package com.wsei.healthcare.backend.auth.application;

import com.wsei.healthcare.backend.auth.api.JwtResponse;
import com.wsei.healthcare.backend.auth.domain.AppAuth;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Slf4j
@Service
public class JwtTokenService implements TokenService {
    private final SecretKey key;
    private final long jwtExpirationSeconds;

    public JwtTokenService(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration.seconds}") long jwtExpirationSeconds
    ) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.jwtExpirationSeconds = jwtExpirationSeconds;
    }

    @Override
    public JwtResponse generateToken(@NotBlank AppAuth auth) {
        Instant now = Instant.now();
        Instant expiration = now.plusSeconds(jwtExpirationSeconds);

        String jwt = Jwts.builder()
                .claim("uid", UUID.randomUUID().toString())
                .subject(auth.name())
                .issuedAt(Date.from(now))
                .expiration(Date.from(expiration))
                .signWith(key)
                .compact();

        return new JwtResponse(jwt, expiration);
    }

    @Override
    public boolean isValid(@NotBlank String token) {
        try {
            parseClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    @Override
    public String resolveToken(String header) {
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }

    @Override
    public String getUsername(@NotBlank String token) {
        return parseClaims(token).getSubject();
    }

    private Claims parseClaims(String token) throws JwtException, IllegalArgumentException {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
