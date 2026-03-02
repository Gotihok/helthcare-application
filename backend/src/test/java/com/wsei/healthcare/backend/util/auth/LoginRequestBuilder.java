package com.wsei.healthcare.backend.util.auth;

import com.wsei.healthcare.backend.api.auth.LoginRequest;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Accessors(chain = true)
public class LoginRequestBuilder implements AuthConstants {

    private String email;
    private String password;

    private LoginRequestBuilder() {}

    public static LoginRequestBuilder getValidDefault() {
        LoginRequestBuilder builder = new LoginRequestBuilder();
        builder.email = VALID_EMAIL;
        builder.password = VALID_PASSWORD;
        return builder;
    }

    public LoginRequest build() {
        return new LoginRequest(email, password);
    }
}
