package com.wsei.healthcare.backend.user.application;

import com.wsei.healthcare.backend.user.api.UserCreateRequest;
import com.wsei.healthcare.backend.user.api.UserEmailUpdateRequest;

public interface UserService {
    //TODO: change from long to domain object
    Long createUser(UserCreateRequest request);

    Long updateEmail(UserEmailUpdateRequest request);
}
