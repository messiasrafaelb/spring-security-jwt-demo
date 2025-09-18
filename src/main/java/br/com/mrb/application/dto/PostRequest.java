package br.com.mrb.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostRequest {

    @NotBlank(message = "{post.title.not-blank}")
    @Size(max = 100, message = "{post.title.size}")
    private String title;

    @NotBlank(message = "{post.content.not-blank}")
    @Size(max = 5000, message = "{post.content.size}")
    private String content;

    @NotNull(message = "{post.author.not-null}")
    @Positive(message = "{post.author.positive}")
    private Long idAuthor;
}
