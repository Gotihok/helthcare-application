package com.wsei.healthcare.backend.auth.application;

import com.wsei.healthcare.backend.auth.domain.AppAuth;
import com.wsei.healthcare.backend.auth.domain.Jwt;

public interface TokenService {
    Jwt generateToken(AppAuth auth);

    String resolveToken(String header);

    boolean isValid(String token);

    String getUsername(String token);
}
