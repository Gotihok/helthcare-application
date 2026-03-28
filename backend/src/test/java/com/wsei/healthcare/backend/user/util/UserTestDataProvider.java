package com.wsei.healthcare.backend.user.util;

import com.wsei.healthcare.backend.shared.util.CommonTestDataProvider;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;

@UtilityClass
public class UserTestDataProvider {

    public String validFirstName() {
        return "firstName";
    }

    public String invalidFirstName() {
        return "";
    }

    public String validLastName() {
        return "lastName";
    }

    public String invalidLastName() {
        return "";
    }

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

    public String validPhoneNumber() {
        return "123456789";
    }

    public String invalidPhoneNumber() {
        return "";
    }

    public LocalDate validBirthDate() {
        return LocalDate.of(1970, 1, 1);
    }

    public LocalDate invalidBirthDate() {
        return null;
    }

}

