package com.wsei.healthcare.backend.domain.user;

import jakarta.persistence.*;
import lombok.Data;
import lombok.experimental.Accessors;

//TODO: model proper info
//TODO: extract principal(UserDetails) to a separate class (into security config) (reduce coupling)
@Entity
@Table(name = "users")
@Data
@Accessors(chain = true)
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private boolean enabled = true;

    @Column(nullable = false)
    private boolean accountNonLocked = true;
}
