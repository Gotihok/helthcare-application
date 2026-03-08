package com.wsei.healthcare.backend.auth.application;

import com.wsei.healthcare.backend.auth.api.LogoutRequest;
import com.wsei.healthcare.backend.auth.api.JwtResponse;
import com.wsei.healthcare.backend.auth.api.LoginRequest;
import com.wsei.healthcare.backend.auth.api.RegisterRequest;
import com.wsei.healthcare.backend.auth.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserPort userPort;
    private final TokenService tokenService;
    private final TokenRevocationService tokenRevocationService;
    private final AuthPort authPort;
    private final AuthIdentityRepository identityRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public JwtResponse register(RegisterRequest registerRequest) {
        Long createdUserId = userPort.createUser(registerRequest);
        identityRepository.save(
                new AuthIdentity()
                        .setUserId(createdUserId)
                        .setEmail(registerRequest.email())
                        .setPasswordHash(passwordEncoder.encode(registerRequest.password()))
                        .setEnabled(true)
                        .setAccountNonLocked(true)
        );
        return authenticateAndBuildTokenResponse(registerRequest.email(), registerRequest.password());
    }

    @Override
    public JwtResponse login(LoginRequest loginRequest) {
        return authenticateAndBuildTokenResponse(loginRequest.email(), loginRequest.password());
    }

    private JwtResponse authenticateAndBuildTokenResponse(String email, String password) {
        AppAuth auth = authPort.authenticate(email, password);
        return tokenService.generateToken(auth);
    }

    @Override
    public void logout(LogoutRequest request) {
        String token = request.token();
        tokenRevocationService.registerLoggedOut(token);
    }
}
