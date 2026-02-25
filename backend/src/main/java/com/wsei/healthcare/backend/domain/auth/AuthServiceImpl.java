package com.wsei.healthcare.backend.domain.auth;

import com.wsei.healthcare.backend.infra.token.JwtTokenService;
import com.wsei.healthcare.backend.infra.token.JwtWithExpiration;
import com.wsei.healthcare.backend.api.auth.JwtResponse;
import com.wsei.healthcare.backend.api.auth.UserLoginRequest;
import com.wsei.healthcare.backend.api.auth.UserLogoutRequest;
import com.wsei.healthcare.backend.api.auth.UserRegisterRequest;
import com.wsei.healthcare.backend.domain.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final JwtTokenService tokenService;
    private final AuthenticationManager authenticationManager;

    @Override
    public JwtResponse register(UserRegisterRequest registerRequest) {
        userService.createUser(registerRequest);
        return authenticateAndBuildTokenResponse(registerRequest.email(), registerRequest.password());
    }

    @Override
    public JwtResponse login(UserLoginRequest loginRequest) {
        return authenticateAndBuildTokenResponse(loginRequest.email(), loginRequest.password());
    }

    private JwtResponse authenticateAndBuildTokenResponse(String email, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email,
                        password
                )
        );

        JwtWithExpiration jwt = tokenService.generateToken(authentication.getName());
        return new JwtResponse(
                jwt.jwt(),
                jwt.expiresAt()
        );
    }

    @Override
    public void logout(UserLogoutRequest logoutRequest) {
        //TODO: add token to invalid ones
    }
}
