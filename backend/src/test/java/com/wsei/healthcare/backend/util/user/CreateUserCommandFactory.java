package com.wsei.healthcare.backend.util.user;

import com.wsei.healthcare.backend.user.api.UserCreateRequest;

public final class CreateUserCommandFactory implements UserConstants {

    public static UserCreateRequest getValidDefault() {
        return UserCreateRequest.builder()
                .email(VALID_EMAIL)
                .password(VALID_PASSWORD)
                .build();
    }
}
