package br.com.mrb.application.dto;

import jakarta.validation.constraints.Size;

public record PostPatchRequest(
        @Size(max = 100, message = "{post.title.size}")
        String title,
        @Size(max = 5000, message = "{post.content.size}")
        String content) {
}
