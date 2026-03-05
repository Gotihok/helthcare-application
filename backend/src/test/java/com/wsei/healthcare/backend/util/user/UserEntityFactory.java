package com.wsei.healthcare.backend.util.user;

import com.wsei.healthcare.backend.domain.user.UserEntity;

public class UserEntityFactory implements UserConstants {

    public static UserEntity getValidDefault() {
        return new UserEntity()
                .setFirstName(FIRST_NAME)
                .setLastName(LAST_NAME)
                .setEmail(VALID_EMAIL)
                .setPassword(VALID_PASSWORD)
                .setPhoneNumber(VALID_PHONE_NUMBER)
                .setDateOfBirth(VALID_DATE_OF_BIRTH)
                .setEnabled(true)
                .setAccountNonLocked(true);
    }
}
