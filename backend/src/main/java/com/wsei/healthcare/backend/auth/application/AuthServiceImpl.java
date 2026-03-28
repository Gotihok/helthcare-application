package com.wsei.healthcare.backend.auth.application;

import com.wsei.healthcare.backend.auth.api.AuthIdentityCreationRequest;
import com.wsei.healthcare.backend.auth.api.JwtResponse;
import com.wsei.healthcare.backend.auth.api.LoginRequest;
import com.wsei.healthcare.backend.auth.api.LogoutRequest;
import com.wsei.healthcare.backend.auth.domain.AppAuth;
import com.wsei.healthcare.backend.auth.domain.AuthIdentity;
import com.wsei.healthcare.backend.auth.domain.AuthIdentityRepository;
import com.wsei.healthcare.backend.auth.domain.AuthPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final TokenService tokenService;
    private final TokenRevocationService tokenRevocationService;
    private final AuthPort authPort;
    private final AuthIdentityRepository identityRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void createAuthIdentity(AuthIdentityCreationRequest request) {
        identityRepository.save(
                new AuthIdentity()
                        .setUserId(request.userId())
                        .setEmail(request.email())
                        .setPasswordHash(passwordEncoder.encode(request.password()))
                        .setEnabled(true)
                        .setAccountNonLocked(true)
        );
    }

    @Override
    public JwtResponse authenticate(LoginRequest loginRequest) {
        AppAuth auth = authPort.authenticate(loginRequest.email(), loginRequest.password());
        return tokenService.generateToken(auth);
    }

    @Override
    public void logout(LogoutRequest request) {
        String token = request.token();
        tokenRevocationService.registerLoggedOut(token);
    }

    @Override
    public void updateUserEmail(Long userId, String newEmail) {
        AuthIdentity identity = identityRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Identity not found"));

        identity.setEmail(newEmail);
        identityRepository.save(identity);
    }
}
