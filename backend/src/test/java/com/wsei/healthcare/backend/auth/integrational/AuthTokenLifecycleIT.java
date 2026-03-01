package com.wsei.healthcare.backend.auth.integrational;

import com.wsei.healthcare.backend.api.auth.LoginRequest;
import com.wsei.healthcare.backend.api.auth.LogoutRequest;
import com.wsei.healthcare.backend.api.auth.RegisterRequest;
import com.wsei.healthcare.backend.auth.builder.AuthDefaults;
import com.wsei.healthcare.backend.auth.builder.LoginRequestBuilder;
import com.wsei.healthcare.backend.auth.builder.LogoutRequestBuilder;
import com.wsei.healthcare.backend.auth.builder.RegisterRequestBuilder;
import com.wsei.healthcare.backend.shared.config.TestControllersConfig;
import com.wsei.healthcare.backend.shared.defaults.SharedDefaults;
import com.wsei.healthcare.backend.shared.integrational.AbstractIntegrationalTest;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(value = TestControllersConfig.TestController.class)
public class AuthTokenLifecycleIT extends AbstractIntegrationalTest implements AuthDefaults, SharedDefaults {
    private static final String TEST_URL = TEST_STRING_ENDPOINT_URL;

    private static final String BASE_URL = "/api/auth";
    private static final String REGISTER_URL = BASE_URL + "/register";
    private static final String LOGIN_URL = BASE_URL + "/login";
    private static final String LOGOUT_URL = BASE_URL + "/logout";

    private static final String DEFAULT_EMAIL = VALID_EMAIL;
    private static final String DEFAULT_PASSWORD = VALID_PASSWORD;

    @Test
    void tokenLifecycleTest() throws Exception {

        // 1. Access protected endpoint without a token
        mockMvc.perform(get(TEST_URL))
                .andExpect(status().isUnauthorized());

        // 2. Register & extract token
        String token = registerAndGetToken();

        // 3. Access protected endpoint with the token
        mockMvc.perform(get(TEST_URL)
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());

        // 4. Logout
        performLogout(token);

        // 5. Old token must be rejected
        mockMvc.perform(get(TEST_URL)
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isUnauthorized());

        // 6. Login
        String newToken = loginAndGetToken();

        // 7. New token works
        mockMvc.perform(get(TEST_URL)
                        .header("Authorization", "Bearer " + newToken))
                .andExpect(status().isOk());
    }

    private String registerAndGetToken() throws Exception {
        RegisterRequest request = RegisterRequestBuilder.getValidDefault()
                .setEmail(DEFAULT_EMAIL)
                .setPassword(DEFAULT_PASSWORD)
                .build();

        String response = mockMvc.perform(post(REGISTER_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        return objectMapper.readTree(response).get("jwt").asString();
    }

    private void performLogout(String token) throws Exception {
        LogoutRequest request = LogoutRequestBuilder.getEmptyDefault()
                .setJwt(token)
                .build();

        mockMvc.perform(post(LOGOUT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNoContent());
    }

    private String loginAndGetToken() throws Exception {
        LoginRequest request = LoginRequestBuilder.getValidDefault()
                .setEmail(DEFAULT_EMAIL)
                .setPassword(DEFAULT_PASSWORD)
                .build();

        String response = mockMvc.perform(post(LOGIN_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        return objectMapper.readTree(response).get("jwt").asString();
    }
}
