package com.wsei.healthcare.backend.auth.infra.integration;

import com.wsei.healthcare.backend.auth.api.RegisterRequest;
import com.wsei.healthcare.backend.user.api.UserCreateRequest;

public interface AuthMapper {
    UserCreateRequest toUserCreateRequest(RegisterRequest registerRequest);
}
