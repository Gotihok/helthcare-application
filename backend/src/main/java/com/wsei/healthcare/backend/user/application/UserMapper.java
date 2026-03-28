package com.wsei.healthcare.backend.user.application;

import com.wsei.healthcare.backend.user.api.UserRegisterRequest;
import com.wsei.healthcare.backend.user.api.UserResponse;
import com.wsei.healthcare.backend.user.domain.AppUser;

public interface UserMapper {
    AppUser toDomain(UserRegisterRequest userCreateRequest);

    UserResponse toDto(AppUser appUser);
}
