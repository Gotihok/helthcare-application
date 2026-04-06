package com.wsei.healthcare.backend.user.integration;

import com.wsei.healthcare.backend.auth.util.LoginRequestBuilder;
import com.wsei.healthcare.backend.user.util.UserRegisterRequestBuilder;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RegisterIT extends AbstractUserIT {

    @Test
    void register_shouldRegisterUser_whenValidCredentials() throws Exception {
        userWebHelper.performRegister(UserRegisterRequestBuilder.getValidDefault().build())
                .andExpect(status().isOk());
        String jwt = authWebHelper.getToken(
                authWebHelper.performLogin(LoginRequestBuilder.getValidDefault().build())
                        .andExpect(status().isOk())
        );
        authWebHelper.shouldAuthorize(jwt);
    }

    @Test
    void register_shouldReturnBadRequest_whenDtoValidationFails() throws Exception {
        userWebHelper.performRegister(UserRegisterRequestBuilder.getInvalidDefault().build())
                .andExpect(status().isBadRequest());
    }

    @Test
    void register_shouldReturnConflict_whenUserAlreadyExists() throws Exception {
        userWebHelper.createDefaultUser();

        userWebHelper.performRegister(UserRegisterRequestBuilder.getValidDefault().build())
                .andExpect(status().isConflict());
    }
}
