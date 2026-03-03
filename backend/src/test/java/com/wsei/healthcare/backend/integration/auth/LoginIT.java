package com.wsei.healthcare.backend.integration.auth;

import com.wsei.healthcare.backend.domain.user.AppUser;
import com.wsei.healthcare.backend.util.auth.AuthConstants;
import com.wsei.healthcare.backend.util.auth.LoginRequestBuilder;
import com.wsei.healthcare.backend.util.user.AppUserFactory;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LoginIT extends AbstractAuthIT {

    @Test
    void login_shouldAuthenticateUser_whenValidCredentials() throws Exception {
        createDefaultUser();

        String jwt = getToken(
                performLogin(LoginRequestBuilder.getValidDefault().build())
                        .andExpect(status().isOk())
        );
        shouldAuthorize(jwt);
    }

    @Test
    void login_shouldReturnBadRequest_whenDtoValidationFails() throws Exception {
        performLogin(LoginRequestBuilder.getInvalidDefault().build())
                .andExpect(status().isBadRequest());
    }

    @Test
    void login_shouldReturnUnauthorized_whenUserDoesntExist() throws Exception {
        performLogin(LoginRequestBuilder.getValidDefault().build())
                .andExpect(status().isUnauthorized());
    }

    @Test
    void login_shouldReturnUnauthorized_whenWrongPassword() throws Exception {
        createDefaultUser();

        performLogin(LoginRequestBuilder.getValidDefault()
                .setPassword(AuthConstants.VALID_PASSWORD + "_wrong")
                .build()
        ).andExpect(status().isUnauthorized());
    }

    @Test
    void login_shouldReturnForbidden_whenAccountDisabled() throws Exception {
        userRepository.save(
                AppUserFactory.getValidDefault()
                        .setEnabled(false)
        );

        AppUser appUser = userRepository.findAppUserByEmail(AuthConstants.VALID_EMAIL)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.save(appUser.setEnabled(false));

        performLogin(LoginRequestBuilder.getValidDefault().build())
                .andExpect(status().isUnauthorized());
    }

    @Test
    void login_shouldReturnForbidden_whenAccountLocked() throws Exception {
        userRepository.save(
                AppUserFactory.getValidDefault()
                        .setAccountNonLocked(false)
        );

        AppUser appUser = userRepository.findAppUserByEmail(AuthConstants.VALID_EMAIL)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.save(appUser.setAccountNonLocked(false));

        performLogin(LoginRequestBuilder.getValidDefault().build())
                .andExpect(status().isUnauthorized());
    }
}
