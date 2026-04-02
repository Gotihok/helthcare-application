package com.wsei.healthcare.backend.user.util;

import com.wsei.healthcare.backend.user.api.UserRegisterRequest;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Setter
@Accessors(chain = true)
public class UserRegisterRequestBuilder {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private LocalDate birthDate;

    private UserRegisterRequestBuilder() {}

    public static UserRegisterRequestBuilder getValidDefault() {
        UserRegisterRequestBuilder builder = new UserRegisterRequestBuilder();
        builder.firstName = UserTestDataProvider.validFirstName();
        builder.lastName = UserTestDataProvider.validLastName();
        builder.email = UserTestDataProvider.validEmail();
        builder.password = UserTestDataProvider.validPassword();
        builder.phoneNumber = UserTestDataProvider.validPhoneNumber();
        builder.birthDate = UserTestDataProvider.validBirthDate();
        return builder;
    }

    public static UserRegisterRequestBuilder getInvalidDefault() {
        UserRegisterRequestBuilder builder = new UserRegisterRequestBuilder();
        builder.firstName = UserTestDataProvider.invalidFirstName();
        builder.lastName = UserTestDataProvider.invalidLastName();
        builder.email = UserTestDataProvider.invalidEmail();
        builder.password = UserTestDataProvider.invalidPassword();
        builder.phoneNumber = UserTestDataProvider.invalidPhoneNumber();
        builder.birthDate = UserTestDataProvider.invalidBirthDate();
        return builder;
    }

    public UserRegisterRequest build() {
        return new UserRegisterRequest(firstName, lastName, email, password, phoneNumber, birthDate);
    }
}
