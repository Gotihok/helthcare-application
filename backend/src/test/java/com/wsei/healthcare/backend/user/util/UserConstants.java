package com.wsei.healthcare.backend.user.util;

import java.time.LocalDate;

public interface UserConstants {
    String VALID_FIRST_NAME = "firstName";
    String VALID_LAST_NAME = "lastName";
    String VALID_EMAIL = "email@example.com";
    String VALID_PASSWORD = "password";
    String VALID_PHONE_NUMBER = "123456789";
    LocalDate VALID_BIRTH_DATE = LocalDate.of(1970, 1, 1);

    String INVALID_FIRST_NAME = "";
    String INVALID_LAST_NAME = "";
    String INVALID_EMAIL = "";
    String INVALID_PASSWORD = "";
    String INVALID_PHONE_NUMBER = "";
    LocalDate INVALID_BIRTH_DATE = null;
}
