package com.wsei.healthcare.backend.auth.infra;

import com.wsei.healthcare.backend.auth.domain.TokenRevocationRepository;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class InMemoryTokenRevocationRepository implements TokenRevocationRepository {
    private final Set<String> tokenRevocationSet = new HashSet<>();

    @Override
    public void save(String token) {
        tokenRevocationSet.add(token);
    }

    @Override
    public boolean contains(String token) {
        return tokenRevocationSet.contains(token);
    }
}
