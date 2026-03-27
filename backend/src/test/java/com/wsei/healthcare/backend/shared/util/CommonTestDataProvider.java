package com.wsei.healthcare.backend.shared.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CommonTestDataProvider {

    public String validEmail() {
        return "email@example.com";
    }

    public String invalidEmail() {
        return "";
    }

    public String validPassword() {
        return "password";
    }

    public String invalidPassword() {
        return "";
    }
}
