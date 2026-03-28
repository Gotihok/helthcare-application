package com.wsei.healthcare.backend.auth.application;

import com.wsei.healthcare.backend.auth.api.AuthIdentityCreationRequest;
import com.wsei.healthcare.backend.auth.api.JwtResponse;
import com.wsei.healthcare.backend.auth.api.LoginRequest;
import com.wsei.healthcare.backend.auth.api.LogoutRequest;

public interface AuthService {
    void createAuthIdentity(AuthIdentityCreationRequest request);

    JwtResponse authenticate(LoginRequest loginRequest);

    void logout(LogoutRequest authentication);

    void updateUserEmail(Long userId, String newEmail);
}
