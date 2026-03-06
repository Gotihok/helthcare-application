package com.wsei.healthcare.backend.auth.application;

import com.wsei.healthcare.backend.auth.api.LogoutRequest;
import com.wsei.healthcare.backend.auth.api.JwtResponse;
import com.wsei.healthcare.backend.auth.api.LoginRequest;
import com.wsei.healthcare.backend.auth.api.RegisterRequest;
import com.wsei.healthcare.backend.auth.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserAdapter userAdapter;
    private final TokenService tokenService;
    private final TokenRevocationService tokenRevocationService;
    private final AuthAdapter authAdapter;

    @Override
    public JwtResponse register(RegisterRequest registerRequest) {
        userAdapter.createUser(registerRequest);
        return authenticateAndBuildTokenResponse(registerRequest.email(), registerRequest.password());
    }

    @Override
    public JwtResponse login(LoginRequest loginRequest) {
        return authenticateAndBuildTokenResponse(loginRequest.email(), loginRequest.password());
    }

    // TODO: change method signature to take AuthCredentials and introduce mapper or inner methods
    //          maybe change the LoginRequest and AuthCredentials to AuthRequest
    //          and make some unification to inline and remove redundant value objects
    private JwtResponse authenticateAndBuildTokenResponse(String email, String password) {
        AppAuth auth = authAdapter.authenticate(email, password);

        // TODO: inline or extract to mapper
        Jwt jwt = tokenService.generateToken(auth);
        return new JwtResponse(
                jwt.getJwt(),
                jwt.getExpiresAt()
        );
    }

    @Override
    public void logout(LogoutRequest request) {
        String token = request.token();
        tokenRevocationService.registerLoggedOut(token);
    }
}
