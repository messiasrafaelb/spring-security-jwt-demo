package br.com.mrb.application.service;

import br.com.mrb.application.dto.PostPatchRequest;
import br.com.mrb.application.dto.PostRequest;
import br.com.mrb.application.dto.PostResponse;
import br.com.mrb.application.exception.AuthorNotFoundException;
import br.com.mrb.application.exception.PostNotFoundException;
import br.com.mrb.application.mapper.PostMapper;
import br.com.mrb.application.repository.PostRepository;
import br.com.mrb.application.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository post_repository;
    private final UserRepository user_repository;
    private final PostMapper mapper;

    public Page<PostResponse> findAll(Pageable pageable) {
        return post_repository.findAll(pageable).map(mapper::toResponse);
    }

    public Page<PostResponse> findPostsByAuthorId(Long authorId, Pageable pageable) {
        return post_repository.findByAuthorId(authorId, pageable).map(mapper::toResponse);
    }

    public PostResponse findPostById(Long postId) {
        var post = post_repository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found with id (" + postId + ")"));
        return mapper.toResponse(post);
    }

    public PostResponse update(Long postId, PostRequest postRequest) {
        var post = post_repository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found with id (" + postId + ")"));
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        return mapper.toResponse(post_repository.save(post));
    }

    public PostResponse create(PostRequest postRequest, Long authorId) {
        var author = user_repository.findById(authorId)
                .orElseThrow(() -> new AuthorNotFoundException("Author not found with id (" + authorId + ")"));
        return mapper.toResponse(post_repository.save(mapper.toEntity(postRequest, author)));
    }

    public Map<String, String > deleteById(Long postId) {
        var post = post_repository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found with id (" + postId + ")"));
        post_repository.delete(post);
        return Map.of("message","Post deleted successfully");
    }

    public PostResponse patch(Long postId, PostPatchRequest postRequest) {
        var post = post_repository.findById(postId)
                .orElseThrow(() -> new PostNotFoundException("Post not found with id (" + postId + ")"));
        if (postRequest.title() != null && !postRequest.title().isBlank())
            post.setTitle(postRequest.title());
        if (postRequest.content() != null && !postRequest.content().isBlank())
            post.setContent(postRequest.content());
        return mapper.toResponse(post_repository.save(post));
    }

}
