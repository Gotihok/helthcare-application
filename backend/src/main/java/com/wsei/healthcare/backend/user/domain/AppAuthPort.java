package com.wsei.healthcare.backend.user.domain;

import com.wsei.healthcare.backend.user.api.UserRegisterRequest;

public interface AppAuthPort {
    void createAuthIdentity(UserRegisterRequest request, Long userId);
}
