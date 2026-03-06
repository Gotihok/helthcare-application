package com.wsei.healthcare.backend.auth.domain;

public interface AuthAdapter {
    AppAuth authenticate(String email, String password);
}
