package com.wsei.healthcare.backend.auth.infra;

import com.wsei.healthcare.backend.auth.domain.AppAuth;
import com.wsei.healthcare.backend.auth.domain.AuthAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthAdapterImpl implements AuthAdapter {
    private final AuthenticationManager authenticationManager;

    @Override
    public AppAuth authenticate(String email, String password) {
        System.out.println("Authenticating with email: " + email);
        System.out.println("Authenticating with password: " + password);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        password
                )
        );
        return new AppAuth(authentication.getName());
    }
}
