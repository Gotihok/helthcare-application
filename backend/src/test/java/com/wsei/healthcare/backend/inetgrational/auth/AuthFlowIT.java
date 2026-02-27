package com.wsei.healthcare.backend.inetgrational.auth;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.testcontainers.postgresql.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
public class AuthFlowIT {

    @RestController
    static class TestController {
        @GetMapping("/api/test")
        public ResponseEntity<String> test() {
            return ResponseEntity.ok("Test");
        }
    }

    @Container
    static PostgreSQLContainer postgres =
            new PostgreSQLContainer("postgres:18")
                    .withUsername("test")
                    .withPassword("test")
                    .withDatabaseName("test");

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);

        registry.add("jwt.secret", () -> "TEST_SECRET-aqigeg29239btu2bg203ig");
        registry.add("jwt.expiration.seconds", () -> 120);
        registry.add("jwt.prefix", () -> "Bearer ");
    }

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void authFullFlowTest() throws Exception {

        // 1. Access protected endpoint without a token
        mockMvc.perform(get("/api/test"))
                .andExpect(status().isUnauthorized());

        // 2. Register & extract token
        String token = registerAndGetToken();

        // 3. Access protected endpoint with the token
        mockMvc.perform(get("/api/test")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());

        // 4. Logout
        performLogout(token);

        // 5. Old token must be rejected
        mockMvc.perform(get("/api/test")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isUnauthorized());

        // 6. Login
        String newToken = loginAndGetToken();

        // 7. New token works
        mockMvc.perform(get("/api/test")
                        .header("Authorization", "Bearer " + newToken))
                .andExpect(status().isOk());
    }

    //TODO: change request hardcoded jsons to providers

    private String registerAndGetToken() throws Exception {
        String response = mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "firstName": "First name",
                                    "lastName": "Last name",
                                    "email": "example1@mail.com",
                                    "password": "test_password"
                                }
                                """))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        return objectMapper.readTree(response).get("jwt").asString();
    }

    private void performLogout(String token) throws Exception {
        mockMvc.perform(post("/api/auth/logout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                               {
                                   "jwt": "%s"
                               }
                               """.formatted(token)))
                .andExpect(status().isNoContent());
    }

    private String loginAndGetToken() throws Exception {
        String response = mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "email": "example1@mail.com",
                                    "password": "test_password"
                                }
                                """))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        return objectMapper.readTree(response).get("jwt").asString();
    }
}
