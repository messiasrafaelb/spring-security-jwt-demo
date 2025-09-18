package br.com.mrb.application.dto;

public record AuthRequest(
        String email,
        String password) {}
