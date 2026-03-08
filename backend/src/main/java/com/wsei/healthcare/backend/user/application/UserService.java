package com.wsei.healthcare.backend.user.application;

import com.wsei.healthcare.backend.user.api.UserCreateRequest;

public interface UserService {
    //TODO: change from entity to domain object
    Long createUser(UserCreateRequest userCreateRequest);
}
