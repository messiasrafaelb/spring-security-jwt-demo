package br.com.mrb.application.dto;

import br.com.mrb.application.model.UserRole;

import java.util.Set;

public record UserResponse(
        Long id,
        String name,
        String email,
        Set<UserRole> roleSet) {}
