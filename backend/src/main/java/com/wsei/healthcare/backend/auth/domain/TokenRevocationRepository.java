package com.wsei.healthcare.backend.auth.domain;

public interface TokenRevocationRepository {
    void save(String token);

    boolean contains(String token);
}
