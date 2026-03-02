package com.wsei.healthcare.backend.application.auth;

import com.wsei.healthcare.backend.api.auth.JwtResponse;
import com.wsei.healthcare.backend.api.auth.LoginRequest;
import com.wsei.healthcare.backend.api.auth.LogoutRequest;
import com.wsei.healthcare.backend.api.auth.RegisterRequest;

public interface AuthService {
    JwtResponse register(RegisterRequest registerRequest);

    JwtResponse login(LoginRequest loginRequest);

    void logout(LogoutRequest logoutRequest);
}
