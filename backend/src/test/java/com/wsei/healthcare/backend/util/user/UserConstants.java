package com.wsei.healthcare.backend.util.user;

import java.time.LocalDate;

public interface UserConstants {
    String FIRST_NAME = "firstName";
    String LAST_NAME = "lastName";
    String VALID_EMAIL = "email@example.com";
    String VALID_PASSWORD = "password";
    String VALID_PHONE_NUMBER = "123456789";
    LocalDate VALID_DATE_OF_BIRTH = LocalDate.of(1970, 1, 1);
}
