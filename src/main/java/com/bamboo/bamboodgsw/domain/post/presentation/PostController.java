package com.bamboo.bamboodgsw.domain.post.presentation;

import com.bamboo.bamboodgsw.domain.post.presentation.dto.PostCreateRequest;
import com.bamboo.bamboodgsw.domain.post.presentation.ro.PostCreateRo;
import com.bamboo.bamboodgsw.domain.post.presentation.ro.PostRo;
import com.bamboo.bamboodgsw.domain.post.presentation.ro.PostListRo;
import com.bamboo.bamboodgsw.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/attachment")
    public Long uploadAttachment(
            @RequestParam MultipartFile request
    ) {
        return postService.uploadAttachment(request);
    }

    @GetMapping(value = "/attachment{attachment-id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> getAttachment(
            @PathVariable("attachment-id") Long attachmentId
    ) {
        return postService.getAttachment(attachmentId);
    }
}
