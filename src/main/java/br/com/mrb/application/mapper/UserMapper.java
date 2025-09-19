package br.com.mrb.application.mapper;

import br.com.mrb.application.dto.UserResponse;
import br.com.mrb.application.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;

@Mapper
@MapperConfig(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "roleSet", expression = "java(user.getRoles().stream().map(ur -> ur.getRole().getName()).collect(Collectors.toSet()))")
    UserResponse toResponse(User user);
}
