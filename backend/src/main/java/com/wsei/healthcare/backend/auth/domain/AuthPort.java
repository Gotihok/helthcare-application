package com.wsei.healthcare.backend.auth.domain;

public interface AuthPort {
    AppAuth authenticate(String email, String password);
}
