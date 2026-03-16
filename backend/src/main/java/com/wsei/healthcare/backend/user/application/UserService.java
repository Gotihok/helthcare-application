package com.wsei.healthcare.backend.user.application;

import com.wsei.healthcare.backend.user.api.UserEmailUpdateRequest;
import com.wsei.healthcare.backend.user.api.UserRegisterRequest;
import com.wsei.healthcare.backend.user.api.UserResponse;

public interface UserService {
    UserResponse register(UserRegisterRequest request);

    UserResponse updateEmail(UserEmailUpdateRequest request);
}
