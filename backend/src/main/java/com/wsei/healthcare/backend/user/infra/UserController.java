package com.wsei.healthcare.backend.user.infra;

import com.wsei.healthcare.backend.user.api.UserApi;
import com.wsei.healthcare.backend.user.api.UserEmailUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserApi userApi;

    @PatchMapping("/email")
    public ResponseEntity<Void> updateEmail(@RequestBody UserEmailUpdateRequest userEmailUpdateRequest) {
        //TODO: secure not owned emails
        userApi.updateUserEmail(userEmailUpdateRequest);
        return ResponseEntity.ok().build();
    }
}
