package com.wsei.healthcare.backend.web.auth;

import com.wsei.healthcare.backend.domain.auth.AuthService;
import com.wsei.healthcare.backend.api.auth.AuthApi;
import com.wsei.healthcare.backend.api.auth.JwtResponse;
import com.wsei.healthcare.backend.api.auth.UserLoginRequest;
import com.wsei.healthcare.backend.api.auth.UserLogoutRequest;
import com.wsei.healthcare.backend.api.auth.UserRegisterRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//TODO: make error dto for every error http response
//      !!! ESPECIALLY FOR JAKARTA VALIDATION !!!
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
    public ResponseEntity<JwtResponse> register(@RequestBody @Valid UserRegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @Override
    @PostMapping(
            value = "/login",
            produces = "application/json"
    )
    public ResponseEntity<JwtResponse> login(@RequestBody UserLoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @Override
    @PostMapping(
            value = "/logout",
            produces = "application/json"
    )
    public ResponseEntity<Void> logout(@RequestBody UserLogoutRequest request) {
        authService.logout(request);
        return ResponseEntity.ok().build();
    }
}
