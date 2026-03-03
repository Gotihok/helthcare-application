package com.wsei.healthcare.backend.integration.auth;

import com.wsei.healthcare.backend.util.auth.RegisterRequestBuilder;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RegisterIT extends AbstractAuthIT {

    @Test
    void register_shouldRegisterUser_whenValidCredentials() throws Exception {
        String jwt = getToken(
                performRegister(RegisterRequestBuilder.getValidDefault().build())
                .andExpect(status().isOk())
        );
        shouldAuthorize(jwt);
    }

    @Test
    void register_shouldReturnBadRequest_whenDtoValidationFails() throws Exception {
        performRegister(RegisterRequestBuilder.getInvalidDefault().build())
                .andExpect(status().isBadRequest());
    }

    @Test
    void register_shouldReturnConflict_whenUserAlreadyExists() throws Exception {
        createDefaultUser();

        performRegister(RegisterRequestBuilder.getValidDefault().build())
                .andExpect(status().isConflict());
    }
}
