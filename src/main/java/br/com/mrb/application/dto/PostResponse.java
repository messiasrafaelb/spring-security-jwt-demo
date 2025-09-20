package br.com.mrb.application.dto;

import java.time.LocalDateTime;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "posts")
public record PostResponse(
        Long id,
        Long authorId,
        String title,
        String content,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        String authorName) {}
