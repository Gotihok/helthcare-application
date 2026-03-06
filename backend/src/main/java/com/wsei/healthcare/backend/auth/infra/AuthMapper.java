package com.wsei.healthcare.backend.auth.infra;

import com.wsei.healthcare.backend.auth.api.RegisterRequest;
import com.wsei.healthcare.backend.to_move.application.user.CreateUserCommand;

public interface AuthMapper {
    CreateUserCommand toCreateUserCommand(RegisterRequest registerRequest);
}
