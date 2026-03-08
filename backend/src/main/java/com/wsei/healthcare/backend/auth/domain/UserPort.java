package com.wsei.healthcare.backend.auth.domain;

import com.wsei.healthcare.backend.auth.api.RegisterRequest;

public interface UserPort {
    Long createUser(RegisterRequest registerRequest);
}
