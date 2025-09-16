package br.com.mrb.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostRequest {

    @NotBlank(message = "{post.title.not-blank}")
    @Size(max = 30, message = "{post.title.size}")
    private String title;

    @NotBlank(message = "{post.content.not-blank}")
    @Size(max = 255, message = "{post.content.size}")
    private String content;
}
