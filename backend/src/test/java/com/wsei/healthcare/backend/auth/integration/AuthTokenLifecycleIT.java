package com.wsei.healthcare.backend.auth.integration;

import com.wsei.healthcare.backend.auth.util.LoginRequestBuilder;
import com.wsei.healthcare.backend.user.util.RegisterRequestBuilder;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthTokenLifecycleIT extends AbstractAuthIT {

    @Test
    void shouldHandleFullJwtLifecycle() throws Exception {

        // 1. Access a protected endpoint without a valid token
        shouldReject("no_token_generated");

        // 2. Register & auth for the token
        performRegister(RegisterRequestBuilder.getValidDefault().build())
                .andExpect(status().isOk());
        String token = getToken(
                performLogin(LoginRequestBuilder.getValidDefault().build())
                        .andExpect(status().isOk())
        );

        // 3. Access protected endpoint with the token
        shouldAuthorize(token);

        // 4. Logout
        performLogout(token)
                .andExpect(status().isNoContent());

        // 5. Old token must be rejected
        shouldReject(token);

        // 6. Login
        String newToken = getToken(
                performLogin(LoginRequestBuilder.getValidDefault().build())
                        .andExpect(status().isOk())
        );

        // 7. New token works
        shouldAuthorize(newToken);
    }

    //TODO: test auth with email change
}
