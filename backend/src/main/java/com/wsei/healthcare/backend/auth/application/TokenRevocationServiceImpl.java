package com.wsei.healthcare.backend.auth.application;

import com.wsei.healthcare.backend.auth.domain.TokenRevocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenRevocationServiceImpl implements TokenRevocationService {
    private final TokenRevocationRepository tokenRevocationRepository;

    @Override
    public void registerLoggedOut(String token) {
        tokenRevocationRepository.save(token);
    }

    @Override
    public boolean isLoggedOut(String token) {
        return tokenRevocationRepository.contains(token);
    }
}
