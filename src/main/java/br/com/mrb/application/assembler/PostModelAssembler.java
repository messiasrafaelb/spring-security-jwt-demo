package br.com.mrb.application.assembler;

import br.com.mrb.application.controller.PostController;
import br.com.mrb.application.dto.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
@RequiredArgsConstructor
public class PostModelAssembler {

    private final PagedResourcesAssembler<PostResponse> pagedResourcesAssembler;

    private List<Link> baseLinks(PostResponse post, Pageable pageable) {
        return List.of(
                linkTo(methodOn(PostController.class).getPostById(post.id())).withSelfRel(),
                linkTo(methodOn(PostController.class).updatePostById(post.id(), null)).withRel("update"),
                linkTo(methodOn(PostController.class).patchPostById(post.id(), null)).withRel("patch"),
                linkTo(methodOn(PostController.class).deletePostById(post.id())).withRel("delete"),
                linkTo(methodOn(PostController.class).getPostsByAuthorId(post.authorId(), pageable)).withRel("author-posts"),
                linkTo(methodOn(PostController.class).getAllPosts(pageable)).withRel("posts")
        );
    }

    public EntityModel<PostResponse> toModel(PostResponse post, Pageable pageable) {
        return EntityModel.of(post, baseLinks(post, pageable));
    }

    public PagedModel<EntityModel<PostResponse>> toPagedModel(Page<PostResponse> page, Pageable pageable) {
        return pagedResourcesAssembler.toModel(page, post -> toModel(post, pageable));
    }

    public EntityModel<Map<String, String>> deletedResponse(Map<String, String> map, Pageable pageable) {
        return EntityModel.of(map, linkTo(methodOn(PostController.class).getAllPosts(pageable)).withRel("posts"));
    }
}
