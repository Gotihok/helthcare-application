package com.wsei.healthcare.backend.auth.integration;

import com.wsei.healthcare.backend.auth.domain.AuthIdentity;
import com.wsei.healthcare.backend.auth.domain.AuthIdentityRepository;
import com.wsei.healthcare.backend.auth.util.AuthTestDataProvider;
import com.wsei.healthcare.backend.auth.util.LoginRequestBuilder;
import com.wsei.healthcare.backend.user.util.UserRegisterRequestBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LoginIT extends AbstractAuthIT {

    @Autowired
    protected AuthIdentityRepository authIdentityRepository;

    @Test
    void login_shouldAuthenticateUser_whenValidCredentials() throws Exception {
        userWebHelper.createDefaultUser();

        String jwt = authWebHelper.getToken(
                authWebHelper.performLogin(LoginRequestBuilder.getValidDefault().build())
                        .andExpect(status().isOk())
        );
        authWebHelper.shouldAuthorize(jwt);
    }

    @Test
    void login_shouldReturnBadRequest_whenDtoValidationFails() throws Exception {
        authWebHelper.performLogin(LoginRequestBuilder.getInvalidDefault().build())
                .andExpect(status().isBadRequest());
    }

    @Test
    void login_shouldReturnUnauthorized_whenUserDoesntExist() throws Exception {
        authWebHelper.performLogin(LoginRequestBuilder.getValidDefault().build())
                .andExpect(status().isUnauthorized());
    }

    @Test
    void login_shouldReturnUnauthorized_whenWrongPassword() throws Exception {
        userWebHelper.createDefaultUser();

        authWebHelper.performLogin(LoginRequestBuilder.getValidDefault()
                .setPassword(AuthTestDataProvider.validPassword() + "_wrong")
                .build()
        ).andExpect(status().isUnauthorized());
    }

    @Test
    void login_shouldReturnForbidden_whenAccountDisabled() throws Exception {
        userWebHelper.performRegister(UserRegisterRequestBuilder.getValidDefault().build());
        AuthIdentity identity = authIdentityRepository.findAuthIdentityByEmail(AuthTestDataProvider.validEmail())
                .orElseThrow(() -> new RuntimeException("Identity is not created"));
        identity.setEnabled(false);
        authIdentityRepository.save(identity);

        authWebHelper.performLogin(LoginRequestBuilder.getValidDefault().build())
                .andExpect(status().isUnauthorized());
    }

    @Test
    void login_shouldReturnForbidden_whenAccountLocked() throws Exception {
        userWebHelper.performRegister(UserRegisterRequestBuilder.getValidDefault().build());
        AuthIdentity identity = authIdentityRepository.findAuthIdentityByEmail(AuthTestDataProvider.validEmail())
                .orElseThrow(() -> new RuntimeException("Identity is not created"));
        identity.setAccountNonLocked(false);
        authIdentityRepository.save(identity);

        authWebHelper.performLogin(LoginRequestBuilder.getValidDefault().build())
                .andExpect(status().isUnauthorized());
    }
}
