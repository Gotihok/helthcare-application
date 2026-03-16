package com.wsei.healthcare.backend.auth.infra.web;

import com.wsei.healthcare.backend.auth.api.AuthApi;
import com.wsei.healthcare.backend.auth.api.JwtResponse;
import com.wsei.healthcare.backend.auth.api.LoginRequest;
import com.wsei.healthcare.backend.auth.api.LogoutRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthApi authApi;

//    @PostMapping(
//            value = "/register",
//            produces = "application/json"
//    )
//    public ResponseEntity<JwtResponse> register(@RequestBody @Valid UserRegisterRequest request) {
//        return ResponseEntity.ok(authApi.register(request));
//    }

    @PostMapping(
            value = "/login",
            produces = "application/json"
    )
    public ResponseEntity<JwtResponse> login(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok(authApi.login(request));
    }

    @PostMapping(
            value = "/logout",
            produces = "application/json"
    )
    public ResponseEntity<Void> logout(Authentication authentication) {
        authApi.logout(new LogoutRequest(
                Objects.requireNonNull(authentication.getCredentials()).toString())
        );
        return ResponseEntity.noContent().build();
    }
}
