package com.wsei.healthcare.backend.user.api;

import lombok.Builder;
import lombok.experimental.Accessors;

@Builder
@Accessors(chain = true)
public record UserCreateRequest(
        String firstName,
        String lastName,
        String email,
        String password
) {
}
