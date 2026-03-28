package com.wsei.healthcare.backend.user.util;

import com.wsei.healthcare.backend.user.api.UserEmailUpdateRequest;
import lombok.Setter;
import lombok.experimental.Accessors;

@Setter
@Accessors(chain = true)
public class UserEmailUpdateRequestBuilder {

    String oldEmail;
    String newEmail;

    private UserEmailUpdateRequestBuilder() {}

    public static UserEmailUpdateRequestBuilder getEmpty() {
        return new UserEmailUpdateRequestBuilder();
    }

    public UserEmailUpdateRequest build() {
        return new UserEmailUpdateRequest(oldEmail, newEmail);
    }
}
