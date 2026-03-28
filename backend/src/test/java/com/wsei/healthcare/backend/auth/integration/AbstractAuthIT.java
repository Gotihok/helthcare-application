package com.wsei.healthcare.backend.auth.integration;

import com.wsei.healthcare.backend.auth.domain.AuthIdentityRepository;
import com.wsei.healthcare.backend.auth.util.AuthWebHelper;
import com.wsei.healthcare.backend.shared.integration.AbstractIntegrationalTest;
import com.wsei.healthcare.backend.user.domain.UserRepository;
import com.wsei.healthcare.backend.user.util.UserWebHelper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractAuthIT extends AbstractIntegrationalTest {

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected AuthIdentityRepository authIdentityRepository;

    @Autowired
    protected AuthWebHelper authWebHelper;

    @Autowired
    protected UserWebHelper userWebHelper;

    @Transactional
    @AfterEach
    void cleanDatabase() {
        userRepository.deleteAll();
        authIdentityRepository.deleteAll();
    }
}
