package com.wsei.healthcare.backend.auth.domain;

import lombok.Data;
import lombok.experimental.Accessors;

//TODO: implement event driven email update when user domain changes email
@Data
@Accessors(chain = true)
public class AuthIdentity {
    private Long id;
    private Long userId;
    private String email;
    private String passwordHash;

    private boolean enabled;
    private boolean accountNonLocked;

//    private Date createdAt;
//    private Date updatedAt;
}
