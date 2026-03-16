package com.wsei.healthcare.backend.auth.integration;

import com.wsei.healthcare.backend.auth.util.LoginRequestBuilder;
import com.wsei.healthcare.backend.auth.util.UserEmailUpdateRequestBuilder;
import com.wsei.healthcare.backend.user.api.UserEmailUpdateRequest;
import com.wsei.healthcare.backend.user.util.RegisterRequestBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EmailChangeIT extends AbstractAuthIT {

    //TODO: move
    private static final String UPDATE_EMAIL_URL = "/api/user/email";

    @Test
    void shouldAuthenticate_whenEmailChanged() throws Exception {
        performRegister(RegisterRequestBuilder.getValidDefault().build())
                .andExpect(status().isOk());
        String token = getToken(
                performLogin(LoginRequestBuilder.getValidDefault().build())
                        .andExpect(status().isOk())
        );

        shouldAuthorize(token);

        String newEmail = VALID_EMAIL + "changed";
        performEmailChange(
                token,
                UserEmailUpdateRequestBuilder.getEmptyDefault()
                        .setOldEmail(VALID_EMAIL)
                        .setNewEmail(newEmail)
                        .build()
        ).andExpect(status().isOk());

        shouldReject(token);

        String newToken = getToken(
                performLogin(LoginRequestBuilder.getValidDefault()
                        .setEmail(newEmail).build())
                        .andExpect(status().isOk())
        );

        shouldAuthorize(newToken);
    }

    //TODO: extract somewhere more appropriate
    private ResultActions performEmailChange(String token, UserEmailUpdateRequest request) throws Exception {
        return mockMvc.perform(patch(UPDATE_EMAIL_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .header("Authorization", "Bearer " + token));
    }
}
