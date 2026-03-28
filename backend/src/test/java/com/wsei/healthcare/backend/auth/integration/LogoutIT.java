package com.wsei.healthcare.backend.auth.integration;

import com.wsei.healthcare.backend.auth.util.AuthTestDataProvider;
import com.wsei.healthcare.backend.auth.util.LoginRequestBuilder;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LogoutIT extends AbstractAuthIT {

    @Test
    void logout_shouldUnauthorize_whenAuthorizedJwt() throws Exception {
        // User creation
        userWebHelper.createDefaultUser();

        // Login
        String jwt = authWebHelper.getToken(
                authWebHelper.performLogin(LoginRequestBuilder.getValidDefault().build())
                        .andExpect(status().isOk())
        );
        authWebHelper.shouldAuthorize(jwt);

        // Logout
        authWebHelper.performLogout(jwt)
                .andExpect(status().isNoContent());
        authWebHelper.shouldReject(jwt);
    }

    @Test
    void logout_shouldReturnUnauthorized_whenJwtIsUnauthenticated() throws Exception {
        authWebHelper.performLogout(AuthTestDataProvider.jwtStub())
                .andExpect(status().isUnauthorized());
    }
}
