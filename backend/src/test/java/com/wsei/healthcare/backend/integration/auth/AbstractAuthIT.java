package com.wsei.healthcare.backend.integration.auth;

import com.wsei.healthcare.backend.api.auth.LoginRequest;
import com.wsei.healthcare.backend.api.auth.LogoutRequest;
import com.wsei.healthcare.backend.api.auth.RegisterRequest;
import com.wsei.healthcare.backend.application.user.UserService;
import com.wsei.healthcare.backend.domain.user.UserMapper;
import com.wsei.healthcare.backend.domain.user.UserRepository;
import com.wsei.healthcare.backend.shared.integration.AbstractIntegrationalTest;
import com.wsei.healthcare.backend.util.auth.AuthConstants;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AbstractAuthIT extends AbstractIntegrationalTest implements AuthConstants {

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected UserService userService;

    @Transactional
    @AfterEach
    void cleanDatabase() {
        userRepository.deleteAll();
    }

    @RestController
    protected static class TestController {
        @GetMapping(TEST_STRING_ENDPOINT_URL)
        public ResponseEntity<String> string() {
            return ResponseEntity.ok("Test");
        }
    }

    // JWT Auth verification
    protected void shouldAuthorize(String token) throws Exception {
        callTestEndpoint(token).andExpect(status().isOk());
    }

    protected void shouldReject(String token) throws Exception {
        callTestEndpoint(token).andExpect(status().isUnauthorized());
    }

    private ResultActions callTestEndpoint(String token) throws Exception {
        return mockMvc.perform(get(TEST_STRING_ENDPOINT_URL)
                        .header("Authorization", "Bearer " + token));
    }

    // JWT Extraction
    protected String getToken(ResultActions request) throws Exception {
        String response = request.andReturn().getResponse().getContentAsString();
        return objectMapper.readTree(response).get("jwt").asString();
    }

    // Auth endpoints
    protected ResultActions performRegister(RegisterRequest request) throws Exception {
        return mockMvc.perform(post(REGISTER_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));
    }

    protected ResultActions performLogin(LoginRequest request) throws Exception {
        return mockMvc.perform(post(LOGIN_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));
    }

    protected ResultActions performLogout(LogoutRequest request) throws Exception {
        return mockMvc.perform(post(LOGOUT_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)));
    }
}
