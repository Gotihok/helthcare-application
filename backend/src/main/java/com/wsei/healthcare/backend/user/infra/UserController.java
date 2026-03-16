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

@RestController
@RequestMapping("/api/user")
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
}
