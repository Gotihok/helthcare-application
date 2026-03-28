package com.wsei.healthcare.backend.auth.util;

import com.wsei.healthcare.backend.auth.api.LoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Component
@RequiredArgsConstructor
public class AuthWebHelper {

    private static final String BASE_AUTH_URL = "/api/auth";
    private static final String LOGIN_URL = BASE_AUTH_URL + "/login";
    private static final String LOGOUT_URL = BASE_AUTH_URL + "/logout";

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    // JWT Auth verification
    public void shouldAuthorize(String token) throws Exception {
        callTestEndpoint(token).andExpect(status().isOk());
    }

    public void shouldReject(String token) throws Exception {
        callTestEndpoint(token).andExpect(status().isUnauthorized());
    }

    private ResultActions callTestEndpoint(String token) throws Exception {
        return mockMvc.perform(get(TEST_STRING_ENDPOINT_URL)
                .header("Authorization", "Bearer " + token));
    }

    // Auth endpoints
    public ResultActions performLogin(LoginRequest request) throws Exception {
        return mockMvc.perform(post(LOGIN_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));
    }

    public ResultActions performLogout(String token) throws Exception {
        return mockMvc.perform(post(LOGOUT_URL)
                .header("Authorization", "Bearer " + token));
    }

    // JWT Extraction
    public String getToken(ResultActions request) throws Exception {
        String response = request.andReturn().getResponse().getContentAsString();
        return objectMapper.readTree(response).get("jwt").asString();
    }

    // Test controller to check auth flows
    public final static String TEST_STRING_ENDPOINT_URL = "/test/string";
    @RestController
    protected static class TestController {
        @GetMapping(TEST_STRING_ENDPOINT_URL)
        public ResponseEntity<String> string() {
            return ResponseEntity.ok("Test");
        }
    }
}
