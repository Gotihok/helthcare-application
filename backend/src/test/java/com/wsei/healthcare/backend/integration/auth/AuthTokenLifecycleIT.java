package com.wsei.healthcare.backend.integration.auth;

import com.wsei.healthcare.backend.util.auth.LoginRequestBuilder;
import com.wsei.healthcare.backend.util.auth.RegisterRequestBuilder;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AuthTokenLifecycleIT extends AbstractAuthIT {

    @Test
    void shouldHandleFullJwtLifecycle() throws Exception {

        // 1. Access a protected endpoint without a valid token
        shouldReject("no_token_generated");

        // 2. Register & extract token
        String token = getToken(
                performRegister(RegisterRequestBuilder.getValidDefault().build())
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
}
