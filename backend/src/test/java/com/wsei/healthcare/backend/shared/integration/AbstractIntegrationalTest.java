package com.wsei.healthcare.backend.shared.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.postgresql.PostgreSQLContainer;

@Tag("integration")
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

        registry.add("cors.origins.allowed", () -> "stub_because_no_consumer_in_tests");
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void cleanDatabase() {
        jdbcTemplate.execute("""
        DO $$
        DECLARE
            r RECORD;
        BEGIN
            FOR r IN (SELECT tablename FROM pg_tables WHERE schemaname = 'public')
            LOOP
                EXECUTE 'TRUNCATE TABLE ' || quote_ident(r.tablename) || ' CASCADE';
            END LOOP;
        END $$;
    """);
    }
}
