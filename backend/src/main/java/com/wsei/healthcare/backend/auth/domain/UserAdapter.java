package com.wsei.healthcare.backend.auth.domain;

import com.wsei.healthcare.backend.auth.api.RegisterRequest;

public interface UserAdapter {
    void createUser(RegisterRequest registerRequest);
}
