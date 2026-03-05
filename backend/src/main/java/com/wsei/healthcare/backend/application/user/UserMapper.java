package com.wsei.healthcare.backend.application.user;

import com.wsei.healthcare.backend.domain.user.UserEntity;

public interface UserMapper {
    UserEntity toEntity(CreateUserCommand createUserCommand);
}
