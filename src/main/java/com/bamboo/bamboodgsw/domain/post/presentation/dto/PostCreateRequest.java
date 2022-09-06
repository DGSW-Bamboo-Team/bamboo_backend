package com.bamboo.bamboodgsw.domain.post.presentation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @AllArgsConstructor
@Builder
public class PostCreateRequest {

    private String content;

    private String[] hashTags;
}
