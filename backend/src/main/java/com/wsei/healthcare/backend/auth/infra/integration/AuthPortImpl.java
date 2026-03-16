package com.wsei.healthcare.backend.auth.infra.integration;

import com.wsei.healthcare.backend.auth.domain.AppAuth;
import com.wsei.healthcare.backend.auth.domain.AuthPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthPortImpl implements AuthPort {
    private final AuthenticationManager authenticationManager;

    @Override
    public AppAuth authenticate(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        password
                )
        );
        return new AppAuth(authentication.getName());
    }
}
