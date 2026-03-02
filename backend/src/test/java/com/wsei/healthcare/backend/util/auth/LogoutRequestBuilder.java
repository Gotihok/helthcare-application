package com.wsei.healthcare.backend.util.auth;

import com.wsei.healthcare.backend.api.auth.LogoutRequest;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Accessors(chain = true)
public class LogoutRequestBuilder implements AuthConstants {

    private String jwt;

    private LogoutRequestBuilder() {}

    public static LogoutRequestBuilder getNoTokenDefault() {
        return new LogoutRequestBuilder();
    }

    public LogoutRequest build() {
        return new LogoutRequest(jwt);
    }
}
