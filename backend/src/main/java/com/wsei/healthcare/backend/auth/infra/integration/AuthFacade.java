package com.wsei.healthcare.backend.auth.infra.integration;

import com.wsei.healthcare.backend.auth.api.*;
import com.wsei.healthcare.backend.auth.application.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

//TODO: maybe move to application layer
@Component
@RequiredArgsConstructor
public class AuthFacade implements AuthApi {

    private final AuthService authService;

    @Override
    public JwtResponse register(@RequestBody @Valid RegisterRequest request) {
        return authService.register(request);
    }

    @Override
    public JwtResponse login(@RequestBody @Valid LoginRequest request) {
        return authService.login(request);
    }

    @Override
    public void logout(LogoutRequest authentication) {
        authService.logout(authentication);
    }
}
