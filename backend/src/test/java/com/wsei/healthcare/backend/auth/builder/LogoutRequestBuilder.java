package com.wsei.healthcare.backend.auth.builder;

import com.wsei.healthcare.backend.api.auth.LogoutRequest;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Accessors(chain = true)
public class LogoutRequestBuilder implements AuthDefaults {

    private String jwt;

    private LogoutRequestBuilder() {}

    public static LogoutRequestBuilder getEmptyDefault() {
        return new LogoutRequestBuilder();
    }

    public LogoutRequest build() {
        return new LogoutRequest(jwt);
    }
}
