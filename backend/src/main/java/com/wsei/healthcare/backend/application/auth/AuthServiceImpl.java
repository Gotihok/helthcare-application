package com.wsei.healthcare.backend.application.auth;

import com.wsei.healthcare.backend.api.auth.JwtResponse;
import com.wsei.healthcare.backend.api.auth.LoginRequest;
import com.wsei.healthcare.backend.api.auth.RegisterRequest;
import com.wsei.healthcare.backend.application.token.JwtTokenService;
import com.wsei.healthcare.backend.application.token.TokenRevocationService;
import com.wsei.healthcare.backend.application.user.UserService;
import com.wsei.healthcare.backend.domain.user.AppUser;
import com.wsei.healthcare.backend.domain.user.UserRepository;
import com.wsei.healthcare.backend.infra.security.Jwt;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final JwtTokenService jwtTokenService;
    private final TokenRevocationService tokenRevocationService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final AuthMapper authMapper;

    @Override
    public JwtResponse register(RegisterRequest registerRequest) {
        userService.createUser(
                authMapper.toCreateUserCommand(registerRequest)
        );
        return authenticateAndBuildTokenResponse(registerRequest.email(), registerRequest.password());
    }

    @Override
    public JwtResponse login(LoginRequest loginRequest) {
        return authenticateAndBuildTokenResponse(loginRequest.email(), loginRequest.password());
    }

    private JwtResponse authenticateAndBuildTokenResponse(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        password
                )
        );

        Jwt jwt = jwtTokenService.generateToken(authentication.getName());
        AppUser user = userRepository.findAppUserByEmail(authentication.getName())
                .orElseThrow(() -> new IllegalStateException("Authenticated user not found"));
        return new JwtResponse(
                jwt.getJwt(),
                jwt.getExpiresAt(),
                user.getRole().name()
        );
    }

    @Override
    public void logout(Authentication authentication) {
        String token = (String) authentication.getCredentials();
        tokenRevocationService.registerLoggedOut(token);
    }
}
