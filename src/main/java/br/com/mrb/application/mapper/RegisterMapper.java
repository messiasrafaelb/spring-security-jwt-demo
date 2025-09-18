package br.com.mrb.application.mapper;

import br.com.mrb.application.dto.RegisterRequest;
import br.com.mrb.application.dto.RegisterResponse;
import br.com.mrb.application.model.Role;
import br.com.mrb.application.model.User;
import br.com.mrb.application.model.UserRole;
import org.mapstruct.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Mapper
@MapperConfig(componentModel = "spring")
public interface RegisterMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "userRoles", expression = "java(new java.util.HashSet<>())")
    User toEntity(RegisterRequest request, @Context Role role, @Context PasswordEncoder encoder);

    RegisterResponse toResponse(User user);

    @AfterMapping
    default void addRole(
    @MappingTarget User user, @Context Role role,
    @Context PasswordEncoder encoder, RegisterRequest request) {
        user.setPassword(encoder.encode(request.getPassword()));
        var ur = new UserRole(user, role);
        user.getRoles().add(ur);
    }
}
