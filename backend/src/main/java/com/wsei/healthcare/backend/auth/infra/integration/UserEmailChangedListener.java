package com.wsei.healthcare.backend.auth.infra.integration;

import com.wsei.healthcare.backend.auth.application.AuthService;
import com.wsei.healthcare.backend.user.api.UserEmailChangedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEmailChangedListener {

    private final AuthService authService;

    @EventListener
    public void handle(UserEmailChangedEvent event) {
        authService.updateUserEmail(event.getUserId(), event.getNewEmail());
    }
}
