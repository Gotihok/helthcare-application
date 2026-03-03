package com.wsei.healthcare.backend.shared.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;
import tools.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Testcontainers
public abstract class AbstractIntegrationalTest {
    // --- Singleton Postgres Container ---
    public static class SingletonPostgresContainer extends PostgreSQLContainer {
        private static final SingletonPostgresContainer INSTANCE = new SingletonPostgresContainer();

        private SingletonPostgresContainer() {
            super("postgres:18");
            withDatabaseName("test");
            withUsername("test");
            withPassword("test");
        }

        public static SingletonPostgresContainer getInstance() {
            return INSTANCE;
        }

        @Override
        public void stop() {
            // Do nothing, let the JVM exit stop the container
        }
    }

    @Container
    static PostgreSQLContainer postgres = SingletonPostgresContainer.getInstance();

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);

        registry.add("jwt.secret", () -> "TEST_SECRET-aqigeg29239btu2bg203ig");
        registry.add("jwt.expiration.seconds", () -> 120);
        registry.add("jwt.prefix", () -> "Bearer ");

        registry.add("cors.origins.allowed", () -> "http://localhost:4200");
    }

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    protected ObjectMapper objectMapper;
}
