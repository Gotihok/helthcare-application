package com.wsei.healthcare.backend.auth.infra;

import com.wsei.healthcare.backend.auth.api.RegisterRequest;
import com.wsei.healthcare.backend.to_move.application.user.CreateUserCommand;
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
                .build();
    }
}
