package com.bamboo.bamboodgsw.domain.post.service;

import com.bamboo.bamboodgsw.domain.post.entity.Post;
import com.bamboo.bamboodgsw.domain.post.entity.PostTag;
import com.bamboo.bamboodgsw.domain.post.entity.Tag;
import com.bamboo.bamboodgsw.domain.post.presentation.dto.PostCreateRequest;
import com.bamboo.bamboodgsw.domain.post.presentation.ro.PostCreateRo;
import com.bamboo.bamboodgsw.domain.post.repository.PostRepository;
import com.bamboo.bamboodgsw.domain.post.repository.PostTagRepository;
import com.bamboo.bamboodgsw.domain.post.repository.TagRepository;
import com.bamboo.bamboodgsw.domain.post.type.PostStatus;
import com.bamboo.bamboodgsw.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final TagRepository tagRepository;
    private final PostTagRepository postTagRepository;

    @Transactional(rollbackFor = Exception.class)
    public PostCreateRo createPost(User user, PostCreateRequest request) {

        // User 는 User 모듈 완성 후 변경 예정
        Post post = Post.builder()
                .user(user)
                .content(request.getContent())
                .status(PostStatus.PENDING)
                .build();
        user.addPost(post);

        String[] tags = request.getHashTags().split("#");

        // 입력받은 해시태그를 List<Tag> 형식으로 변환
        List<Tag> tagList = Arrays.stream(tags).map(it -> Tag.builder()
                .hashTag(it)
                .build()).collect(Collectors.toList());

        // 등록이 안되어있는 해시태그라면 Tag 테이블에 추가
        List<Tag> saveTagList = tagList.stream().filter(
                i -> !i.getHashTag().equals(tagRepository.findByHashTag(i.getHashTag()).orElseThrow().getHashTag())
        ).map(it -> Tag.builder()
                .hashTag(it.getHashTag())
                .build()).collect(Collectors.toList());
        tagRepository.saveAll(saveTagList);

        // 입력받은 해시태그를 PostTag 테이블에 추가
        List<PostTag> savePostTagList = tagList.stream().map(it ->
                PostTag.builder()
                        .post(post)
                        .tag(it)
                        .build()
        ).collect(Collectors.toList());
        postTagRepository.saveAll(savePostTagList);

        return PostCreateRo.builder()
                .postId(post.getPostId())
                .build();
    }

}
