package com.wsei.healthcare.backend.auth.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@Entity
@Table(name = "auth_identity")
public class AuthIdentity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(nullable = false)
    private Long userId;

//    @Column(nullable = false)
    private String email;

//    @Column(nullable = false)
    private String passwordHash;

    private boolean enabled;
    private boolean accountNonLocked;

    //TODO: add fields
//    private Date createdAt;
//    private Date updatedAt;
}
