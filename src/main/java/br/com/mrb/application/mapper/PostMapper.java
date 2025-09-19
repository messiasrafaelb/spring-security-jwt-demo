package br.com.mrb.application.mapper;

import br.com.mrb.application.dto.PostRequest;
import br.com.mrb.application.dto.PostResponse;
import br.com.mrb.application.model.Post;
import br.com.mrb.application.model.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @Mapping(target = "authorName", source = "author.name")
    @Mapping(target = "authorId", source = "author.id")
    PostResponse toResponse(Post post);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Post toEntity(PostRequest postRequest, @Context User author);

    @AfterMapping
    default void setAuthor(@MappingTarget Post post, @Context User author) {
        post.setAuthor(author);
    }
}
