package com.wsei.healthcare.backend.application.auth;

import com.wsei.healthcare.backend.api.auth.RegisterRequest;
import com.wsei.healthcare.backend.application.user.CreateUserCommand;
import org.springframework.stereotype.Component;

@Component
public class AuthMapperImpl implements AuthMapper {
    @Override
    public CreateUserCommand toCreateUserCommand(RegisterRequest registerRequest) {
        return CreateUserCommand.builder()
                .firstName(registerRequest.firstName())
                .lastName(registerRequest.lastName())
                .email(registerRequest.email())
                .password(registerRequest.password())
                .role(registerRequest.role().trim().toUpperCase())
                .build();
    }
}
