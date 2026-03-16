package com.wsei.healthcare.backend.auth.integration;

import com.wsei.healthcare.backend.auth.api.LoginRequest;
import com.wsei.healthcare.backend.auth.domain.AuthIdentityRepository;
import com.wsei.healthcare.backend.auth.util.AuthConstants;
import com.wsei.healthcare.backend.shared.integration.AbstractIntegrationalTest;
import com.wsei.healthcare.backend.user.api.UserRegisterRequest;
import com.wsei.healthcare.backend.user.domain.UserRepository;
import com.wsei.healthcare.backend.user.util.RegisterRequestBuilder;
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
    protected AuthIdentityRepository authIdentityRepository;

    @Transactional
    @AfterEach
    void cleanDatabase() {
        userRepository.deleteAll();
        authIdentityRepository.deleteAll();
    }

    @RestController
    protected static class TestController {
        @GetMapping(TEST_STRING_ENDPOINT_URL)
        public ResponseEntity<String> string() {
            return ResponseEntity.ok("Test");
        }
    }

    //TODO: extract call methods to helper class
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

    // User helper endpoint
    // TODO: extract to user
    protected ResultActions performRegister(UserRegisterRequest request) throws Exception {
        return mockMvc.perform(post(REGISTER_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));
    }

    // Auth endpoints
    protected ResultActions performLogin(LoginRequest request) throws Exception {
        return mockMvc.perform(post(LOGIN_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));
    }

    protected ResultActions performLogout(String token) throws Exception {
        return mockMvc.perform(post(LOGOUT_URL)
                .header("Authorization", "Bearer " + token));
    }

    // Create user
    protected void createDefaultUser() throws Exception {
        performRegister(RegisterRequestBuilder.getValidDefault().build());
    }
}
