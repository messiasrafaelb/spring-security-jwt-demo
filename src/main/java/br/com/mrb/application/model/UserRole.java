package br.com.mrb.application.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users_roles")
@Setter @Getter
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @EqualsAndHashCode.Include
    private User user;

    @ManyToOne
    @JoinColumn(name = "role_id")
    @EqualsAndHashCode.Include
    private Role role;

    public UserRole(User user, Role role) {
        this.user = user;
        this.role = role;
    }
}
