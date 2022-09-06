package com.bamboo.bamboodgsw.domain.post.presentation.ro;

import com.bamboo.bamboodgsw.domain.post.type.PostStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter @AllArgsConstructor
@Builder
public class PostRo {

    private Long postId;

    private String content;

    private PostStatus status;

    private List<TagRo> hashTags;

}
