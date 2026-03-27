package com.wsei.healthcare.backend.user.util;

import com.wsei.healthcare.backend.user.api.UserEmailUpdateRequest;
import com.wsei.healthcare.backend.user.api.UserRegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@Component
@RequiredArgsConstructor
public class UserWebHelper {

    private static final String BASE_USER_URL = "/api/users";
    private static final String REGISTER_URL = BASE_USER_URL + "/register";
    private static final String UPDATE_EMAIL_URL = BASE_USER_URL + "/email";

    private final MockMvc mockMvc;
    private final ObjectMapper objectMapper;

    // Create user
    public void createDefaultUser() throws Exception {
        performRegister(RegisterRequestBuilder.getValidDefault().build());
    }

    // User helper methods
    public ResultActions performRegister(UserRegisterRequest request) throws Exception {
        return mockMvc.perform(post(REGISTER_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)));
    }

    public ResultActions performEmailChange(String token, UserEmailUpdateRequest request) throws Exception {
        return mockMvc.perform(patch(UPDATE_EMAIL_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
                .header("Authorization", "Bearer " + token));
    }
}
