package com.bamboo.bamboodgsw.domain.post.presentation;

import com.bamboo.bamboodgsw.domain.post.presentation.dto.PostCreateRequest;
import com.bamboo.bamboodgsw.domain.post.presentation.ro.PostCreateRo;
import com.bamboo.bamboodgsw.domain.post.service.PostService;
import com.bamboo.bamboodgsw.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public PostCreateRo createPost(
            @RequestBody PostCreateRequest request
    ) {
        return postService.createPost(null, request);
    }
}
