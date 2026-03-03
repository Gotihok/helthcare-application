package com.wsei.healthcare.backend.application.auth;

import com.wsei.healthcare.backend.api.auth.RegisterRequest;
import com.wsei.healthcare.backend.application.user.CreateUserCommand;

public interface AuthMapper {
    CreateUserCommand toCreateUserCommand(RegisterRequest registerRequest);
}
