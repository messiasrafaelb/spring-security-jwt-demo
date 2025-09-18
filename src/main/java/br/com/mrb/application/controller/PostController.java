package br.com.mrb.application.controller;

import br.com.mrb.application.dto.PostPatchRequest;
import br.com.mrb.application.dto.PostRequest;
import br.com.mrb.application.dto.PostResponse;
import br.com.mrb.application.service.PostService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @GetMapping
    public ResponseEntity<Page<PostResponse>> getAllPosts(
            @PageableDefault(page = 0, size = 15, sort = "title", direction = ASC) Pageable pageable) {
        return ResponseEntity.status(OK).body(postService.findAll(pageable));
    }

    @GetMapping("/author/{authorId}")
    public ResponseEntity<Page<PostResponse>> getPostsByAuthorId(
            @PathVariable @Positive(message = "Id must be greater than 0") Long authorId,
            @PageableDefault(page = 0, size = 15, sort = "title", direction = ASC) Pageable pageable) {
        return ResponseEntity.status(OK).body(postService.findPostsByAuthorId(authorId, pageable));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> getPostById(
            @PathVariable @Positive(message = "Id must be greater than 0") Long postId) {
        return ResponseEntity.status(OK).body(postService.findPostById(postId));
    }

    @PostMapping("/author/{authorId}")
    public ResponseEntity<PostResponse> createPost(
            @Valid @RequestBody PostRequest post,
            @PathVariable @Positive(message = "Id must be greater than 0") Long authorId) {
        return ResponseEntity.status(CREATED).body(postService.create(post, authorId));
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostResponse> updatePostById(
            @PathVariable @Positive(message = "Id must be greater than 0") Long postId,
            @Valid @RequestBody PostRequest post) {
        return ResponseEntity.status(OK).body(postService.update(postId, post));
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<PostResponse> patchPostById(
            @PathVariable @Positive(message = "Id must be greater than 0") Long postId,
            @RequestBody PostPatchRequest patchRequest) {
        return ResponseEntity.ok(postService.patch(postId, patchRequest));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<Map<String ,String>> deletePostById(
            @PathVariable @Positive(message = "Id must be greater than 0") Long postId) {
        return ResponseEntity.status(OK).body(postService.deleteById(postId));
    }

}

