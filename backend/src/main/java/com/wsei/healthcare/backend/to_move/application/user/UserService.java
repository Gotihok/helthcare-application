package com.wsei.healthcare.backend.to_move.application.user;

import com.wsei.healthcare.backend.to_move.UserEntity;

public interface UserService {
    //TODO: change from entity to domain object
    void createUser(CreateUserCommand createUserCommand);
}
