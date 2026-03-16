package com.wsei.healthcare.backend.auth.integration;

import com.wsei.healthcare.backend.auth.domain.AuthIdentity;
import com.wsei.healthcare.backend.auth.util.AuthConstants;
import com.wsei.healthcare.backend.auth.util.LoginRequestBuilder;
import com.wsei.healthcare.backend.user.util.RegisterRequestBuilder;
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
        performRegister(RegisterRequestBuilder.getValidDefault().build());
        AuthIdentity identity = authIdentityRepository.findAuthIdentityByEmail(VALID_EMAIL)
                .orElseThrow(() -> new RuntimeException("Identity is not created"));
        identity.setEnabled(false);
        authIdentityRepository.save(identity);

        performLogin(LoginRequestBuilder.getValidDefault().build())
                .andExpect(status().isUnauthorized());
    }

    @Test
    void login_shouldReturnForbidden_whenAccountLocked() throws Exception {
        performRegister(RegisterRequestBuilder.getValidDefault().build());
        AuthIdentity identity = authIdentityRepository.findAuthIdentityByEmail(VALID_EMAIL)
                .orElseThrow(() -> new RuntimeException("Identity is not created"));
        identity.setAccountNonLocked(false);
        authIdentityRepository.save(identity);

        performLogin(LoginRequestBuilder.getValidDefault().build())
                .andExpect(status().isUnauthorized());
    }
}
