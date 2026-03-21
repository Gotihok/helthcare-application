package com.wsei.healthcare.backend.web.user;

import com.wsei.healthcare.backend.api.user.UserMeResponse;
import com.wsei.healthcare.backend.infra.security.UserPrincipal;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping("/me")
    public ResponseEntity<UserMeResponse> me(Authentication authentication) {
        UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
        return ResponseEntity.ok(new UserMeResponse(principal.getUsername(), principal.getRole().name()));
    }
}
