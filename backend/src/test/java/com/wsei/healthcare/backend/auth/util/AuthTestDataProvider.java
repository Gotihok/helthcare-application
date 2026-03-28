package com.wsei.healthcare.backend.auth.util;

import com.wsei.healthcare.backend.shared.util.CommonTestDataProvider;
import lombok.experimental.UtilityClass;

@UtilityClass
public class AuthTestDataProvider {

    public String validEmail() {
        return CommonTestDataProvider.validEmail();
    }

    public String invalidEmail() {
        return CommonTestDataProvider.invalidEmail();
    }

    public String validPassword() {
        return CommonTestDataProvider.validPassword();
    }

    public String invalidPassword() {
        return CommonTestDataProvider.invalidPassword();
    }

    public String jwtStub() {
        return "some.jwt.stub";
    }
}
