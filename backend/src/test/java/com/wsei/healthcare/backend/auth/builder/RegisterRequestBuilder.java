package com.wsei.healthcare.backend.auth.builder;

import com.wsei.healthcare.backend.api.auth.RegisterRequest;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Accessors(chain = true)
public class RegisterRequestBuilder implements AuthDefaults {

    private String firstName;
    private String lastName;
    private String email;
    private String password;

    private RegisterRequestBuilder() {}

    public static RegisterRequestBuilder getValidDefault() {
        RegisterRequestBuilder builder = new RegisterRequestBuilder();
        builder.firstName = VALID_FIRST_NAME;
        builder.lastName = VALID_LAST_NAME;
        builder.email = VALID_EMAIL;
        builder.password = VALID_PASSWORD;
        return builder;
    }

    public RegisterRequest build() {
        return new RegisterRequest(firstName, lastName, email, password);
    }
}
