package com.wsei.healthcare.backend.web.auth;

import com.wsei.healthcare.backend.api.auth.*;
import com.wsei.healthcare.backend.application.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController implements AuthApi {

    private final AuthService authService;

    @Override
    @PostMapping(
            value = "/register",
            produces = "application/json"
    )
    public ResponseEntity<JwtResponse> register(@RequestBody @Valid RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @Override
    @PostMapping(
            value = "/login",
            produces = "application/json"
    )
    public ResponseEntity<JwtResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @Override
    @PostMapping(
            value = "/logout",
            produces = "application/json"
    )
    public ResponseEntity<Void> logout(@RequestBody LogoutRequest request) {
        authService.logout(request);
        return ResponseEntity.noContent().build();
    }
}
