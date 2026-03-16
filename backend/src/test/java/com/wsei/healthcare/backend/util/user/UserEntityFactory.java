package com.wsei.healthcare.backend.util.user;

import com.wsei.healthcare.backend.user.domain.UserEntity;

public class UserEntityFactory implements UserConstants {

    public static UserEntity getValidDefault() {
        return new UserEntity()
                .setFirstName(FIRST_NAME)
                .setLastName(LAST_NAME)
                .setEmail(VALID_EMAIL)
                .setPhoneNumber(VALID_PHONE_NUMBER)
                .setDateOfBirth(VALID_DATE_OF_BIRTH);
    }
}
