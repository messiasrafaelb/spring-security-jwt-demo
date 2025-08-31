package br.com.mrb.application.model;

import jakarta.persistence.*;

import java.util.HashSet;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private RoleName name;

    public enum RoleName {
        ROLE_USER, ROLE_ADMIN;
    }

    @OneToMany(
            mappedBy = "role",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<UserRole> userRole = new HashSet<>();
}
