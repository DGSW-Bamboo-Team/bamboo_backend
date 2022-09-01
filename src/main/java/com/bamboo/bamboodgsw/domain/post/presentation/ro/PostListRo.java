package com.bamboo.bamboodgsw.domain.post.presentation.ro;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter @AllArgsConstructor
@Builder
public class PostListRo {

    private List<PostRo> list;

}
