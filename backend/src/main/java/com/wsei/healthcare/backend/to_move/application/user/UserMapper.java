package com.wsei.healthcare.backend.to_move.application.user;

import com.wsei.healthcare.backend.to_move.UserEntity;

public interface UserMapper {
    UserEntity toEntity(CreateUserCommand createUserCommand);
}
