package com.wsei.healthcare.backend.shared.config;

import com.wsei.healthcare.backend.shared.defaults.SharedDefaults;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@TestConfiguration
public class TestControllersConfig implements SharedDefaults {

    @RestController
    public static class TestController {
        @GetMapping(TEST_STRING_ENDPOINT_URL)
        public ResponseEntity<String> string() {
            return ResponseEntity.ok("Test");
        }
    }
}
