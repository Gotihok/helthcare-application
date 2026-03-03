package com.wsei.healthcare.backend.application.user;

import lombok.Builder;
import lombok.experimental.Accessors;

@Builder
@Accessors(chain = true)
public record CreateUserCommand(
        //TODO: add other info
        String email,
        String password
) {
}
