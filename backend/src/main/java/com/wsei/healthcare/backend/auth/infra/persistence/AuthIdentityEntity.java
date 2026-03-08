package com.wsei.healthcare.backend.auth.infra.persistence;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Entity
@Table(name = "auth_identity")
@Accessors(chain = true)
public class AuthIdentityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String email;

    private String passwordHash;

    private boolean enabled;
    private boolean nonLocked;

    //TODO: add Date metrics
}
