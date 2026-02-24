package com.wsei.healthcare.backend.user.rest.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "User registration request payload")
public record UserRegisterRequest(

        @NotBlank
//        @Size(min = 2, max = 50)
        @Schema(
                description = "User first name",
                example = "Jimmy"
        )
        String firstName,

        @NotBlank
//        @Size(min = 2, max = 50)
        @Schema(
                description = "User last name",
                example = "Neutron"
        )
        String lastName,

        @NotBlank
//        @Email
        @Schema(
                description = "User email address",
                example = "jimmy.neutron@example.com"
        )
        String email,

        @NotBlank
//        @Size(min = 8, max = 64)
        @Schema(
                description = "User password",
                example = "P@ssw0rd123",
                accessMode = Schema.AccessMode.WRITE_ONLY
        )
        String password
) {}
