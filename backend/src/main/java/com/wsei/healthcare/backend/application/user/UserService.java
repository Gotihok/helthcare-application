package com.wsei.healthcare.backend.application.user;

import com.wsei.healthcare.backend.api.auth.RegisterRequest;

public interface UserService {
    void createUser(RegisterRequest registerRequest);
}
