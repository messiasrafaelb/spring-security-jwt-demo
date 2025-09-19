package br.com.mrb.application.controller;

import br.com.mrb.application.assembler.PostModelAssembler;
import br.com.mrb.application.dto.PostPatchRequest;
import br.com.mrb.application.dto.PostRequest;
import br.com.mrb.application.dto.PostResponse;
import br.com.mrb.application.service.PostService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import static org.springframework.data.domain.Sort.Direction.ASC;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/posts")
@Validated
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final PostModelAssembler assembler;

    @GetMapping
    public ResponseEntity<PagedModel<EntityModel<PostResponse>>> getAllPosts(
            @PageableDefault(page = 0, size = 15, sort = "title", direction = ASC) Pageable pageable) {
        Page<PostResponse> page = postService.findAll(pageable);
        var pagedModel = assembler.toPagedModel(page, pageable);
        return ResponseEntity.status(OK).body(pagedModel);
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<PagedModel<EntityModel<PostResponse>>> getPostsByAuthorId(
            @PathVariable @Positive(message = "Id must be greater than 0") Long authorId,
            @PageableDefault(page = 0, size = 15, sort = "title", direction = ASC) Pageable pageable) {
        Page<PostResponse> page = postService.findPostsByAuthorId(authorId, pageable);
        var pagedModel = assembler.toPagedModel(page, pageable);
        return ResponseEntity.status(OK).body(pagedModel);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<EntityModel<PostResponse>> getPostById(
            @PathVariable @Positive(message = "Id must be greater than 0") Long postId) {
        var pageable = PageRequest.of(0, 15, ASC);
        var post = postService.findPostById(postId);
        var model = assembler.toModel(post, pageable);
        return ResponseEntity.status(OK).body(model);
    }

    @PostMapping("/author/{authorId}")
    public ResponseEntity<EntityModel<PostResponse>> createPost(
            @PathVariable @Positive(message = "Id must be greater than 0") Long authorId,
            @Valid @RequestBody PostRequest request) {
        var pageable = PageRequest.of(0, 15, ASC);
        var post = postService.create(authorId, request);
        var model = assembler.toModel(post, pageable);
        return ResponseEntity.status(CREATED).body(model);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<EntityModel<PostResponse>> updatePostById(
            @PathVariable @Positive(message = "Id must be greater than 0") Long postId,
            @Valid @RequestBody PostRequest request) {
        var pageable = PageRequest.of(0, 15, ASC);
        var post = postService.update(postId, request);
        var model = assembler.toModel(post, pageable);
        return ResponseEntity.status(OK).body(model);
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<EntityModel<PostResponse>> patchPostById(
            @PathVariable @Positive(message = "Id must be greater than 0") Long postId,
            @RequestBody PostPatchRequest request) {
        var pageable = PageRequest.of(0, 15, ASC);
        var post = postService.patch( postId, request);
        var model = assembler.toModel(post, pageable);
        return ResponseEntity.status(OK).body(model);
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<EntityModel<Map<String ,String>>> deletePostById(
            @PathVariable @Positive(message = "Id must be greater than 0") Long postId) {
        var pageable = PageRequest.of(0, 15, ASC);
        var map = postService.deleteById(postId);
        var model = assembler.deletedResponse(map, pageable);
        return ResponseEntity.status(OK).body(model);
    }

}

