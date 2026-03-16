package com.wsei.healthcare.backend.auth.infra.integration;

import com.wsei.healthcare.backend.auth.api.*;
import com.wsei.healthcare.backend.auth.application.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@RequiredArgsConstructor
public class AuthFacade implements AuthApi {

    private final AuthService authService;

    @Override
    public void createAuthIdentity(AuthIdentityCreationRequest request) {
        authService.createAuthIdentity(request);
    }

    @Override
    public JwtResponse login(@RequestBody @Valid LoginRequest request) {
        return authService.authenticate(request);
    }

    @Override
    public void logout(LogoutRequest authentication) {
        authService.logout(authentication);
    }
}
