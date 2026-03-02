package com.wsei.healthcare.backend.domain.user;

import com.wsei.healthcare.backend.api.auth.RegisterRequest;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public AppUser toEntity(RegisterRequest registerRequest) {
        return new AppUser()
                .setEmail(registerRequest.email())
                .setPassword(registerRequest.password())
                .setEnabled(true)
                .setAccountNonLocked(true);
    }
}
