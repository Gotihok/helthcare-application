package com.wsei.healthcare.backend.util.user;

import com.wsei.healthcare.backend.domain.user.AppUser;

public class AppUserFactory implements UserConstants {

    public static AppUser getValidDefault() {
        return new AppUser()
                .setEmail(VALID_EMAIL)
                .setPassword(VALID_PASSWORD)
                .setEnabled(true)
                .setAccountNonLocked(true);
    }
}
