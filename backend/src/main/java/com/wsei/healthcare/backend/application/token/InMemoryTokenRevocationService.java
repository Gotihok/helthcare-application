package com.wsei.healthcare.backend.application.token;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class InMemoryTokenRevocationService implements TokenRevocationService {
    private final Set<String> loggedOutTokens = new HashSet<>();

    @Override
    public void registerLoggedOut(String token) {
        loggedOutTokens.add(token);
    }

    @Override
    public boolean isLoggedOut(String token) {
        return loggedOutTokens.contains(token);
    }
}
