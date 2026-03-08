package com.wsei.healthcare.backend.auth.infra.integration;

import com.wsei.healthcare.backend.auth.api.RegisterRequest;
import com.wsei.healthcare.backend.user.api.UserCreateRequest;
import org.springframework.stereotype.Component;

@Component
public class AuthMapperImpl implements AuthMapper {
    @Override
    public UserCreateRequest toUserCreateRequest(RegisterRequest registerRequest) {
        return UserCreateRequest.builder()
                .firstName(registerRequest.firstName())
                .lastName(registerRequest.lastName())
                .email(registerRequest.email())
                .password(registerRequest.password())
                .build();
    }
}
