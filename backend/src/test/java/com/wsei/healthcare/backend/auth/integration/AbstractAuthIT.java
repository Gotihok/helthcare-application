package com.wsei.healthcare.backend.auth.integration;

import com.wsei.healthcare.backend.auth.util.AuthWebHelper;
import com.wsei.healthcare.backend.shared.integration.AbstractIntegrationalTest;
import com.wsei.healthcare.backend.user.util.UserWebHelper;
import org.springframework.beans.factory.annotation.Autowired;

public class AbstractAuthIT extends AbstractIntegrationalTest {

    @Autowired
    protected AuthWebHelper authWebHelper;

    @Autowired
    protected UserWebHelper userWebHelper;
}
