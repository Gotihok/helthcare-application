package com.wsei.healthcare.backend.patient.integration;

import com.wsei.healthcare.backend.auth.util.AuthTokenFactory;
import com.wsei.healthcare.backend.patient.util.PatientWebHelper;
import com.wsei.healthcare.backend.shared.integration.AbstractIntegrationalTest;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractPatientIT extends AbstractIntegrationalTest {

    protected String token;

    @BeforeEach
    public void setUp() throws Exception {
        token = authTokenFactory.createDefaultAuthenticatedUserToken();
    }

    @Autowired
    protected PatientWebHelper patientWebHelper;

    @Autowired
    protected AuthTokenFactory authTokenFactory;
}
