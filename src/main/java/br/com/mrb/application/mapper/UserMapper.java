package br.com.mrb.application.mapper;

import br.com.mrb.application.dto.UserResponse;
import br.com.mrb.application.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "roleSet",
           expression = "java(user.getRoles().stream().map(ur -> ur.getRole().getType()).collect(java.util.stream.Collectors.toSet()))")
    UserResponse toResponse(User user);
}
