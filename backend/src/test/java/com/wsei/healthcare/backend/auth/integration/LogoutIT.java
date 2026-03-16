package com.wsei.healthcare.backend.auth.integration;

import com.wsei.healthcare.backend.auth.util.LoginRequestBuilder;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LogoutIT extends AbstractAuthIT {

    @Test
    void logout_shouldUnauthorize_whenAuthorizedJwt() throws Exception {
        // User creation
        createDefaultUser();

        // Login
        String jwt = getToken(
                performLogin(LoginRequestBuilder.getValidDefault().build())
                        .andExpect(status().isOk())
        );
        shouldAuthorize(jwt);

        // Logout
        performLogout(jwt)
                .andExpect(status().isNoContent());
        shouldReject(jwt);
    }

    @Test
    void logout_shouldReturnUnauthorized_whenJwtIsUnauthenticated() throws Exception {
        performLogout(JWT_TOKEN_STUB)
                .andExpect(status().isUnauthorized());
    }
}
