package com.wsei.healthcare.backend.user.integration;

import com.wsei.healthcare.backend.auth.util.LoginRequestBuilder;
import com.wsei.healthcare.backend.user.util.UserEmailUpdateRequestBuilder;
import com.wsei.healthcare.backend.user.util.UserRegisterRequestBuilder;
import com.wsei.healthcare.backend.user.util.UserTestDataProvider;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class EmailChangeIT extends AbstractUserIT {

    @Test
    void shouldAuthenticate_whenEmailChanged() throws Exception {
        userWebHelper.performRegister(UserRegisterRequestBuilder.getValidDefault().build())
                .andExpect(status().isOk());
        String token = authWebHelper.getToken(
                authWebHelper.performLogin(LoginRequestBuilder.getValidDefault().build())
                        .andExpect(status().isOk())
        );

        authWebHelper.shouldAuthorize(token);

        String newEmail = UserTestDataProvider.validEmail() + "changed";
        userWebHelper.performEmailChange(
                token,
                UserEmailUpdateRequestBuilder.getEmpty()
                        .setOldEmail(UserTestDataProvider.validEmail())
                        .setNewEmail(newEmail)
                        .build()
        ).andExpect(status().isOk());

        authWebHelper.shouldReject(token);

        String newToken = authWebHelper.getToken(
                authWebHelper.performLogin(LoginRequestBuilder.getValidDefault()
                        .setEmail(newEmail).build())
                        .andExpect(status().isOk())
        );

        authWebHelper.shouldAuthorize(newToken);
    }

    @Test
    void shouldReturnForbidden_whenEmailIsNotOwned() throws Exception {
        String secondEmail = UserTestDataProvider.validEmail() + "changed";

        userWebHelper.performRegister(UserRegisterRequestBuilder.getValidDefault().build())
                .andExpect(status().isOk());
        userWebHelper.performRegister(UserRegisterRequestBuilder.getValidDefault().setEmail(secondEmail).build())
                .andExpect(status().isOk());

        String token = authWebHelper.getToken(
                authWebHelper.performLogin(LoginRequestBuilder.getValidDefault().build())
                        .andExpect(status().isOk())
        );

        userWebHelper.performEmailChange(
                token,
                UserEmailUpdateRequestBuilder.getEmpty()
                        .setOldEmail(secondEmail)
                        .setNewEmail(secondEmail + "asd")
                        .build()
        ).andExpect(status().isForbidden());
    }
}
