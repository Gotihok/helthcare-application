package com.wsei.healthcare.backend.integration.auth;

import com.wsei.healthcare.backend.domain.user.AppUser;
import com.wsei.healthcare.backend.domain.user.UserMapper;
import com.wsei.healthcare.backend.util.auth.RegisterRequestBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class RegisterIT extends AbstractAuthIT {

    @Autowired
    private UserMapper userMapper;

    @Test
    void registerUser_shouldRegisterUser_whenValidCredentials() throws Exception {
        String jwt = getToken(
                performRegister(RegisterRequestBuilder.getValidDefault().build())
                .andExpect(status().isOk())
        );
        shouldAuthorize(jwt);
    }

    @Test
    void registerUser_shouldReturnBadRequest_whenDtoValidationFails() throws Exception {
        performRegister(RegisterRequestBuilder.getInvalidDefault().build())
                .andExpect(status().isBadRequest());
    }

    @Test
    void registerUser_shouldReturnConflict_whenUserAlreadyExists() throws Exception {
        AppUser appUser = userMapper.toEntity(
                RegisterRequestBuilder.getValidDefault().build()
        );
        userRepository.save(appUser);

        performRegister(RegisterRequestBuilder.getValidDefault().build())
                .andExpect(status().isConflict());
    }
}
