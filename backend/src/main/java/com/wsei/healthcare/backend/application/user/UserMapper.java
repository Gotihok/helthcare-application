package com.wsei.healthcare.backend.application.user;

import com.wsei.healthcare.backend.domain.user.AppUser;

public interface UserMapper {
    AppUser toEntity(CreateUserCommand createUserCommand);
}
