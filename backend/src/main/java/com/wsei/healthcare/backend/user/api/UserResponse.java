package com.wsei.healthcare.backend.user.api;

import java.time.LocalDate;

//TODO: rename with specification User<?>Response
public record UserResponse(
        Long id,
        String email,
        String firstName,
        String lastName,
        String phoneNumber,
        LocalDate birthDate
) {
}
