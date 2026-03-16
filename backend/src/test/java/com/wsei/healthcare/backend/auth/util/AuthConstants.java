package com.wsei.healthcare.backend.auth.util;

public interface AuthConstants {

    // ENDPOINTS
    String TEST_STRING_ENDPOINT_URL = "/test/string";

    //TODO: move to user
    String BASE_USER_URL = "/api/user";
    String REGISTER_URL = BASE_USER_URL + "/register";

    String BASE_AUTH_URL = "/api/auth";
    String LOGIN_URL = BASE_AUTH_URL + "/login";
    String LOGOUT_URL = BASE_AUTH_URL + "/logout";

    // FIELD VALUES
    //TODO: move to user constants or leave doubled
    String VALID_EMAIL = "email@example.com";
    String VALID_PASSWORD = "password";
    String INVALID_EMAIL = "";
    String INVALID_PASSWORD = "";

    String JWT_TOKEN_STUB = "some.jwt.stub";
}
