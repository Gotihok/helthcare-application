package com.wsei.healthcare.backend.infra.token;

public interface TokenRevocationService {
    void registerLoggedOut(String token);
    boolean isLoggedOut(String token);
}
