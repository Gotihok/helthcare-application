package com.wsei.healthcare.backend.application.token;

public interface TokenRevocationService {
    void registerLoggedOut(String token);
    boolean isLoggedOut(String token);
}
