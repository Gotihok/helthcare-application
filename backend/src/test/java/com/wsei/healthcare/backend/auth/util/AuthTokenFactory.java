package com.wsei.healthcare.backend.auth.util;

import com.wsei.healthcare.backend.user.api.UserRegisterRequest;
import com.wsei.healthcare.backend.user.util.UserRegisterRequestBuilder;
import com.wsei.healthcare.backend.user.util.UserWebHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Component
@RequiredArgsConstructor
public final class AuthTokenFactory {

    private final AuthWebHelper authWebHelper;
    private final UserWebHelper userWebHelper;

    // Base user registration and auth with token retrieval
    public String createDefaultAuthenticatedUserToken() throws Exception {
        return createAuthenticatedUserToken(UserRegisterRequestBuilder.getValidDefault().build());
    }

    public String createAuthenticatedUserToken(UserRegisterRequest request) throws Exception {
        userWebHelper.performRegister(request)
                .andExpect(status().isOk());
        return authWebHelper.getToken(
                authWebHelper.performLogin(LoginRequestBuilder.getValidDefault().build())
                        .andExpect(status().isOk())
        );
    }
}
