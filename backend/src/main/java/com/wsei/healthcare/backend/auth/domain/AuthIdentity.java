package com.wsei.healthcare.backend.auth.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Entity
@Table(name = "auth_identity")
@Accessors(chain = true)
public class AuthIdentity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String email;
    private String passwordHash;

    private boolean enabled;
    private boolean accountNonLocked;

//    private Date createdAt;
//    private Date updatedAt;
}
