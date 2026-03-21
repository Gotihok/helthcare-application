package com.wsei.healthcare.backend.application.user;

import lombok.Builder;
import lombok.experimental.Accessors;

@Builder
@Accessors(chain = true)
public record CreateUserCommand(
        String firstName,
        String lastName,
        String email,
        String password,
        String role
) {
}
