package br.com.mrb.application.mapper;

import br.com.mrb.application.dto.UserResponse;
import br.com.mrb.application.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MapperConfig;

@Mapper
@MapperConfig(componentModel = "spring")
public interface UserMapper {
    UserResponse toResponse(User user);
}
