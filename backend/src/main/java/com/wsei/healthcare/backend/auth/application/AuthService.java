package com.wsei.healthcare.backend.auth.application;

import com.wsei.healthcare.backend.auth.api.LogoutRequest;
import com.wsei.healthcare.backend.auth.api.JwtResponse;
import com.wsei.healthcare.backend.auth.api.LoginRequest;
import com.wsei.healthcare.backend.auth.api.RegisterRequest;

public interface AuthService {
    JwtResponse register(RegisterRequest registerRequest);

    JwtResponse login(LoginRequest loginRequest);

    void logout(LogoutRequest authentication);

    void updateUserEmail(Long userId, String newEmail);
}
