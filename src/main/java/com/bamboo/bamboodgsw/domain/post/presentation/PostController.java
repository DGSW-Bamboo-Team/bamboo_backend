package com.bamboo.bamboodgsw.domain.post.presentation;

import com.bamboo.bamboodgsw.domain.post.entity.Tag;
import com.bamboo.bamboodgsw.domain.post.presentation.dto.PostCreateRequest;
import com.bamboo.bamboodgsw.domain.post.presentation.ro.PostCreateRo;
import com.bamboo.bamboodgsw.domain.post.presentation.ro.PostRo;
import com.bamboo.bamboodgsw.domain.post.presentation.ro.PostListRo;
import com.bamboo.bamboodgsw.domain.post.service.PostService;
import com.bamboo.bamboodgsw.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final UserRepository userRepository;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}")
    public PostCreateRo createPost(
            @PathVariable("id") String id,
            @RequestBody PostCreateRequest request
    ) {
        return postService.createPost(id, request);
    }

    @GetMapping("/")
    public PostListRo getAllPost(
            @RequestParam("page") int page
    ) {
        return postService.getAllPost(page);
    }

    @GetMapping("/{post-id}")
    public PostRo getPostById(
            @PathVariable("post-id") Long postId
    ) {
        return postService.getPostById(postId);
    }

}
