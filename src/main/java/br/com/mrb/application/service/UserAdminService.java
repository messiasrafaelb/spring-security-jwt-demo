package br.com.mrb.application.service;

import br.com.mrb.application.dto.UserResponse;
import br.com.mrb.application.exception.RoleNotFoundException;
import br.com.mrb.application.exception.UserNotFoundException;
import br.com.mrb.application.mapper.UserMapper;
import br.com.mrb.application.model.RoleType;
import br.com.mrb.application.model.UserRole;
import br.com.mrb.application.repository.RoleRepository;
import br.com.mrb.application.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserAdminService {

    private final UserRepository user_repository;
    private final RoleRepository role_repository;
    private final UserMapper mapper;

    public Page<UserResponse> findAll(Pageable pageable) {
        return user_repository.findAll(pageable).map(mapper::toResponse);
    }

    public UserResponse changeRole(Long userId, String roleName) {
        var user = user_repository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id (" + userId + ")"));
        var role = role_repository.findByType(RoleType.valueOf(roleName))
                .orElseThrow(() -> new RoleNotFoundException("Role not found"));
        UserRole ur = new UserRole(user, role);
        user.getRoles().add(ur);
        return mapper.toResponse(user);
    }

    public Map<String, String> deleteById(Long userId) {
        var post = user_repository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id (" + userId + ")"));
        user_repository.delete(post);
        return Map.of("message","User deleted successfully");
    }

}
