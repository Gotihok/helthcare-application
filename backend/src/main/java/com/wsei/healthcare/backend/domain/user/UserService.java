package com.wsei.healthcare.backend.domain.user;

import com.wsei.healthcare.backend.api.auth.RegisterRequest;

public interface UserService {
    void createUser(RegisterRequest registerRequest);
}
