package com.wsei.healthcare.backend.doctor.integration;

import com.wsei.healthcare.backend.auth.util.AuthTokenFactory;
import com.wsei.healthcare.backend.doctor.util.DoctorWebHelper;
import com.wsei.healthcare.backend.shared.integration.AbstractIntegrationalTest;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDoctorIT extends AbstractIntegrationalTest {

    protected String token;

    @BeforeEach
    public void setUp() throws Exception {
        token = authTokenFactory.createDefaultAuthenticatedUserToken();
    }

    @Autowired
    protected DoctorWebHelper doctorWebHelper;

    @Autowired
    protected AuthTokenFactory authTokenFactory;
}
