package com.wsei.healthcare.backend.auth.infra.integration;

import com.wsei.healthcare.backend.auth.api.RegisterRequest;
import com.wsei.healthcare.backend.auth.domain.UserPort;
import com.wsei.healthcare.backend.user.api.UserApi;
import com.wsei.healthcare.backend.user.application.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserPortImpl implements UserPort {
    // TODO: refactor when user service changes
    private final AuthMapper authMapper;
    private final UserApi userApi;

    @Override
    public Long createUser(RegisterRequest registerRequest) {
        return userApi.createUser(
                authMapper.toUserCreateRequest(registerRequest)
        );
    }
}
