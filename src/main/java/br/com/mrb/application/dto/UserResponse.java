package br.com.mrb.application.dto;

import br.com.mrb.application.model.RoleType;
import org.springframework.hateoas.server.core.Relation;
import java.util.Set;

@Relation(collectionRelation = "users")
public record UserResponse(
        Long id,
        String name,
        String email,
        Set<RoleType> roleSet) {}
