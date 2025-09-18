package br.com.mrb.application.service;

import br.com.mrb.application.dto.RegisterRequest;
import br.com.mrb.application.dto.RegisterResponse;
import br.com.mrb.application.exception.EmailAlreadyExistsException;
import br.com.mrb.application.exception.RoleNotFoundException;
import br.com.mrb.application.mapper.RegisterMapper;
import br.com.mrb.application.model.Role;
import br.com.mrb.application.repository.RoleRepository;
import br.com.mrb.application.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import static br.com.mrb.application.model.Role.RoleName;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final UserRepository user_repository;
    private final RoleRepository role_repository;
    private final PasswordEncoder passwordEncoder;
    private final RegisterMapper mapper;

    public RegisterResponse save(RegisterRequest request) {
        if (user_repository.findByEmail(request.getEmail()).isPresent())
            throw new EmailAlreadyExistsException("Email already exists");
        Role role = role_repository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new RoleNotFoundException("Role not found"));
        var user = mapper.toEntity(request, role, passwordEncoder);
        user_repository.save(user);
        return mapper.toResponse(user);
    }
}
