package com.wsei.healthcare.backend.util.user;

import com.wsei.healthcare.backend.application.user.CreateUserCommand;

public class CreateUserCommandFactory implements UserConstants {

    public static CreateUserCommand getValidDefault() {
        return CreateUserCommand.builder()
                .email(VALID_EMAIL)
                .password(VALID_PASSWORD)
                .build();
    }
}
