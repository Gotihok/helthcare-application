package com.wsei.healthcare.backend.user.api;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Builder
@Accessors(chain = true)
public record UserRegisterRequest(
        String firstName,
        String lastName,
        String email,
        String password,
        String phoneNumber,

        @NotNull
        LocalDate birthDate
) {
}
