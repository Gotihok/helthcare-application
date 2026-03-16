package com.wsei.healthcare.backend.user.util;

import com.wsei.healthcare.backend.user.api.UserRegisterRequest;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Setter
@Accessors(chain = true)
public class RegisterRequestBuilder implements UserConstants {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private LocalDate birthDate;

    private RegisterRequestBuilder() {}

    public static RegisterRequestBuilder getValidDefault() {
        RegisterRequestBuilder builder = new RegisterRequestBuilder();
        builder.firstName = VALID_FIRST_NAME;
        builder.lastName = VALID_LAST_NAME;
        builder.email = VALID_EMAIL;
        builder.password = VALID_PASSWORD;
        builder.phoneNumber = VALID_PHONE_NUMBER;
        builder.birthDate = VALID_BIRTH_DATE;
        return builder;
    }

    public static RegisterRequestBuilder getInvalidDefault() {
        RegisterRequestBuilder builder = new RegisterRequestBuilder();
        builder.firstName = INVALID_FIRST_NAME;
        builder.lastName = INVALID_LAST_NAME;
        builder.email = INVALID_EMAIL;
        builder.password = INVALID_PASSWORD;
        builder.phoneNumber = INVALID_PHONE_NUMBER;
        builder.birthDate = INVALID_BIRTH_DATE;
        return builder;
    }

    public UserRegisterRequest build() {
        return new UserRegisterRequest(firstName, lastName, email, password, phoneNumber, birthDate);
    }
}
