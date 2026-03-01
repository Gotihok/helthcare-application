package com.wsei.healthcare.backend.auth.integrational;

import com.wsei.healthcare.backend.shared.integrational.AbstractIntegrationalTest;
import org.junit.jupiter.api.Test;

public class UserRegisterIT extends AbstractIntegrationalTest {

    @Test
    void registerUser_shouldRegisterUser_whenValidCredentials() throws Exception {
        //
    }

    @Test
    void registerUser_shouldReturnBadRequest_whenDtoValidationFails() throws Exception {
        //
    }

    @Test
    void registerUser_shouldReturnConflict_whenUserAlreadyExists() throws Exception {
        //
    }
}
