package com.wsei.healthcare.backend.user.infra;

import com.wsei.healthcare.backend.user.api.UserApi;
import com.wsei.healthcare.backend.user.api.UserEmailUpdateRequest;
import com.wsei.healthcare.backend.user.api.UserRegisterRequest;
import com.wsei.healthcare.backend.user.api.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserApi userApi;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(@RequestBody @Valid UserRegisterRequest request) {
        return ResponseEntity.ok(userApi.register(request));
    }

    @PatchMapping("/email")
    public ResponseEntity<UserResponse> updateEmail(
            @RequestBody UserEmailUpdateRequest request,
            Authentication authentication
    ) {
        return ResponseEntity.ok(userApi.updateUserEmail(request, authentication.getName()));
    }

    //TODO: implement
    @GetMapping("/me")
    public ResponseEntity<UserResponse> getAuthenticatedUser(Authentication auth) {
        return null;
    }

    //TODO: implement
    @PostMapping("/me")
    public ResponseEntity<UserResponse> updateAuthenticatedUser(Authentication auth) {
        return null;
    }

    //TODO: implement
    @DeleteMapping("/me")
    public ResponseEntity<UserResponse> deleteAuthenticatedUser(Authentication auth) {
        return null;
    }

    //TODO: implement
    //TODO: add pagination
    @GetMapping("")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return null;
    }

    //TODO: implement
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        return null;
    }
}
