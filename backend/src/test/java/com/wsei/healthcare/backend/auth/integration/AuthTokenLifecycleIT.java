package com.wsei.healthcare.backend.auth.integration;

import com.wsei.healthcare.backend.auth.util.LoginRequestBuilder;
import com.wsei.healthcare.backend.user.util.UserRegisterRequestBuilder;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthTokenLifecycleIT extends AbstractAuthIT {

    @Test
    void shouldHandleFullJwtLifecycle() throws Exception {

        // 1. Access a protected endpoint without a valid token
        authWebHelper.shouldReject("no_token_generated");

        // 2. Register & auth for the token
        userWebHelper.performRegister(UserRegisterRequestBuilder.getValidDefault().build())
                .andExpect(status().isOk());
        String token = authWebHelper.getToken(
                authWebHelper.performLogin(LoginRequestBuilder.getValidDefault().build())
                        .andExpect(status().isOk())
        );

        // 3. Access protected endpoint with the token
        authWebHelper.shouldAuthorize(token);

        // 4. Logout
        authWebHelper.performLogout(token)
                .andExpect(status().isNoContent());

        // 5. Old token must be rejected
        authWebHelper.shouldReject(token);

        // 6. Login
        String newToken = authWebHelper.getToken(
                authWebHelper.performLogin(LoginRequestBuilder.getValidDefault().build())
                        .andExpect(status().isOk())
        );

        // 7. New token works
        authWebHelper.shouldAuthorize(newToken);
    }
}
