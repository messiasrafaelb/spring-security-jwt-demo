package br.com.mrb.application.dto;

import java.time.LocalDateTime;

public record PostResponse(
        Long id,
        Long authorId,
        String title,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String authorName) {}
