package com.wsei.healthcare.backend.integration.auth;

import com.wsei.healthcare.backend.util.auth.AuthConstants;
import com.wsei.healthcare.backend.util.auth.LoginRequestBuilder;
import com.wsei.healthcare.backend.util.auth.LogoutRequestBuilder;
import com.wsei.healthcare.backend.util.auth.RegisterRequestBuilder;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//TODO: implement
public class LogoutIT extends AbstractAuthIT {

    @Test
    void logout_shouldUnauthorize_whenAuthorizedJwt() throws Exception {
        // User creation
        userService.createUser(RegisterRequestBuilder.getValidDefault().build());

        // Login
        String jwt = getToken(
                performLogin(LoginRequestBuilder.getValidDefault().build())
                        .andExpect(status().isOk())
        );
        shouldAuthorize(jwt);

        // Logout
        performLogout(
                LogoutRequestBuilder.getNoTokenDefault()
                .setJwt(jwt)
                .build()
        ).andExpect(status().isNoContent());
        shouldReject(jwt);
    }

    @Test
    void logout_shouldReturnUnauthorized_whenJwtIsUnauthenticated() throws Exception {
        performLogout(
                LogoutRequestBuilder.getNoTokenDefault()
                        .setJwt(AuthConstants.JWT_TOKEN_STUB)
                        .build()
        ).andExpect(status().isNoContent());
    }
}
