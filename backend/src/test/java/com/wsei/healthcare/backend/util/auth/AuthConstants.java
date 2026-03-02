package com.wsei.healthcare.backend.util.auth;

import java.time.Instant;

public interface AuthConstants {

    // ENDPOINTS
    String TEST_STRING_ENDPOINT_URL = "/test/string";

    String BASE_URL = "/api/auth";
    String REGISTER_URL = BASE_URL + "/register";
    String LOGIN_URL = BASE_URL + "/login";
    String LOGOUT_URL = BASE_URL + "/logout";

    // FIELD VALUES
    String VALID_FIRST_NAME = "firstName";
    String VALID_LAST_NAME = "lastName";
    String VALID_EMAIL = "email@example.com";
    String VALID_PASSWORD = "password";

    String INVALID_FIRST_NAME = "";
    String INVALID_LAST_NAME = "";
    String INVALID_EMAIL = "";
    String INVALID_PASSWORD = "";

    String JWT_STUB = "some.jwt.stub";
    Instant JWT_EXPIRATION_STUB = Instant.now().plusSeconds(3600);
}
