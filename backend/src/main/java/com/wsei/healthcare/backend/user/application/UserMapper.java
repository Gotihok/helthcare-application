package com.wsei.healthcare.backend.user.application;

import com.wsei.healthcare.backend.user.api.UserCreateRequest;
import com.wsei.healthcare.backend.user.domain.AppUser;

public interface UserMapper {
    AppUser toDomain(UserCreateRequest userCreateRequest);
}
