package com.wsei.healthcare.backend.domain.auth;

import com.wsei.healthcare.backend.api.auth.JwtResponse;
import com.wsei.healthcare.backend.api.auth.UserLoginRequest;
import com.wsei.healthcare.backend.api.auth.UserLogoutRequest;
import com.wsei.healthcare.backend.api.auth.UserRegisterRequest;

public interface AuthService {
    JwtResponse register(UserRegisterRequest registerRequest);

    JwtResponse login(UserLoginRequest loginRequest);

    void logout(UserLogoutRequest logoutRequest);
}
