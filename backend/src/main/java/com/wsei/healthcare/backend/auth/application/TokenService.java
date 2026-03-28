package com.wsei.healthcare.backend.auth.application;

import com.wsei.healthcare.backend.auth.api.JwtResponse;
import com.wsei.healthcare.backend.auth.domain.AppAuth;

public interface TokenService {
    JwtResponse generateToken(AppAuth auth);

    String resolveToken(String header);

    boolean isValid(String token);

    String getUsername(String token);
}
