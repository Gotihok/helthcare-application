package com.wsei.healthcare.backend.user.infra;

import com.wsei.healthcare.backend.auth.api.AuthApi;
import com.wsei.healthcare.backend.auth.api.AuthIdentityCreationRequest;
import com.wsei.healthcare.backend.user.api.UserRegisterRequest;
import com.wsei.healthcare.backend.user.domain.AppAuthPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AppAuthPortImpl implements AppAuthPort {
    private final AuthApi authApi;

    @Override
    public void createAuthIdentity(UserRegisterRequest request, Long userId) {
        authApi.createAuthIdentity(new AuthIdentityCreationRequest(
                userId,
                request.email(),
                request.password()
        ));
    }
}
