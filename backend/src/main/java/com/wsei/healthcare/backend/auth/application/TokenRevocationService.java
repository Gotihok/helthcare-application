package com.wsei.healthcare.backend.auth.application;

public interface TokenRevocationService {
    void registerLoggedOut(String token);
    boolean isLoggedOut(String token);
}
