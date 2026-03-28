package com.wsei.healthcare.backend.auth.util;

import com.wsei.healthcare.backend.auth.api.LoginRequest;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Accessors(chain = true)
public class LoginRequestBuilder {

    private String email;
    private String password;

    private LoginRequestBuilder() {}

    public static LoginRequestBuilder getValidDefault() {
        LoginRequestBuilder builder = new LoginRequestBuilder();
        builder.email = AuthTestDataProvider.validEmail();
        builder.password = AuthTestDataProvider.validPassword();
        return builder;
    }

    public static LoginRequestBuilder getInvalidDefault() {
        LoginRequestBuilder builder = new LoginRequestBuilder();
        builder.email = AuthTestDataProvider.invalidEmail();
        builder.password = AuthTestDataProvider.invalidPassword();
        return builder;
    }

    public LoginRequest build() {
        return new LoginRequest(email, password);
    }
}
