package com.bamboo.bamboodgsw.domain.post.service;

import com.bamboo.bamboodgsw.domain.post.repository.PostRepository;
import com.bamboo.bamboodgsw.domain.post.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final TagRepository tagRepository;

}
