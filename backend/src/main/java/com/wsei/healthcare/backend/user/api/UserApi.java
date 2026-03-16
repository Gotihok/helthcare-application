package com.wsei.healthcare.backend.user.api;

public interface UserApi {
    //TODO: change to user response when introduced
    Long createUser(UserCreateRequest userCreateRequest);

    Long updateUserEmail(UserEmailUpdateRequest userEmailUpdateRequest);
}
