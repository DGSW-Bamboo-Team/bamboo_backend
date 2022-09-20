package com.bamboo.bamboodgsw.domain.post.service;

import com.bamboo.bamboodgsw.domain.post.entity.Post;
import com.bamboo.bamboodgsw.domain.post.entity.PostTag;
import com.bamboo.bamboodgsw.domain.post.entity.Tag;
import com.bamboo.bamboodgsw.domain.post.exception.PostNotFoundException;
import com.bamboo.bamboodgsw.domain.post.exception.TagNotFoundException;
import com.bamboo.bamboodgsw.domain.post.presentation.dto.PostCreateRequest;
import com.bamboo.bamboodgsw.domain.post.presentation.ro.PostCreateRo;
import com.bamboo.bamboodgsw.domain.post.presentation.ro.PostRo;
import com.bamboo.bamboodgsw.domain.post.presentation.ro.PostListRo;
import com.bamboo.bamboodgsw.domain.post.presentation.ro.TagRo;
import com.bamboo.bamboodgsw.domain.post.repository.PostRepository;
import com.bamboo.bamboodgsw.domain.post.repository.PostTagRepository;
import com.bamboo.bamboodgsw.domain.post.repository.TagRepository;
import com.bamboo.bamboodgsw.domain.post.type.PostStatus;
import com.bamboo.bamboodgsw.domain.user.entity.User;
import com.bamboo.bamboodgsw.domain.user.repository.UserRepository;
import com.bamboo.bamboodgsw.global.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final TagRepository tagRepository;
    private final PostTagRepository postTagRepository;
    private final UserRepository userRepository;

    @Transactional(rollbackFor = Exception.class)
    public PostCreateRo createPost(String id, PostCreateRequest request) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new BusinessException(HttpStatus.NOT_FOUND, "유저를 찾을 수 없습니다"));

        // User 는 User 모듈 완성 후 변경 예정
        Post post = Post.builder()
                .user(user)
                .content(request.getContent())
                .status(PostStatus.PENDING)
                .postTagList(new ArrayList<>())
                .build();
        user.addPost(post);

        List<Tag> saveTagList = Arrays.stream(request.getHashTags()).filter(
                i -> !tagRepository.existsByHashTag(i)
        ).map(it -> Tag.builder()
                .hashTag(it)
                .build()).collect(Collectors.toList());
        tagRepository.saveAll(saveTagList);

        // 입력받은 해시태그를 PostTag 테이블에 추가
        for(String hashTag : request.getHashTags()) {
            Tag tag = tagRepository.findByHashTag(hashTag)
                    .orElseThrow(() -> {
                        throw TagNotFoundException.EXCEPTION;
                    });
            post.addMappings(PostTag.builder()
                            .tag(tag)
                    .build());
        }

        return PostCreateRo.builder()
                .postId(post.getPostId())
                .build();
    }

    @Transactional(readOnly = true)
    public PostListRo getAllPost(int page) {
        Pageable pageable = PageRequest.of(page - 1, 10, Sort.Direction.DESC, "postId");
        Page<Post> postList = postRepository.findAllByStatus(PostStatus.ALLOWED, pageable);

        List<PostRo> list = postList.stream().map(it ->
                new PostRo(it.getPostId(), it.getContent(),
                        it.getStatus(), postTagRepository.findAllByPost(it)
                        .stream().map(item ->
                                new TagRo(item.getTag().getTagId(), item.getTag().getHashTag())
                        ).collect(Collectors.toList()))
        ).collect(Collectors.toList());

        return PostListRo.builder()
                .list(list)
                .build();
    }

    @Transactional(readOnly = true)
    public PostRo getPostById(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> {
                    throw PostNotFoundException.EXCEPTION;
                });

        List<TagRo> hashTags = postTagRepository.findAllByPost(post)
                .stream().map(it ->
                    new TagRo(it.getTag().getTagId(), it.getTag().getHashTag())
                ).collect(Collectors.toList());

        return new PostRo(post.getPostId(), post.getContent(), post.getStatus(), hashTags);
    }


}
