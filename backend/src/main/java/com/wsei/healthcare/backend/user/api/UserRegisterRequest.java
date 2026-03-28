package com.wsei.healthcare.backend.user.api;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

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
