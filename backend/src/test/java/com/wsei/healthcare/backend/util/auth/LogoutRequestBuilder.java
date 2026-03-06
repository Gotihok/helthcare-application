package com.wsei.healthcare.backend.util.auth;

import com.wsei.healthcare.backend.auth.api.LogoutRequest;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Accessors(chain = true)
public class LogoutRequestBuilder implements AuthConstants {

    private String token;

    private LogoutRequestBuilder() {}

    public static LogoutRequestBuilder getEmptyDefault() {
        return new LogoutRequestBuilder();
    }

    public LogoutRequest build() {
        return new LogoutRequest(token);
    }
}
