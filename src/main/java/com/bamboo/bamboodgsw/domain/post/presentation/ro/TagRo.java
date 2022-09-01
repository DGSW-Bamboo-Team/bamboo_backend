package com.bamboo.bamboodgsw.domain.post.presentation.ro;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @AllArgsConstructor
@Builder
public class TagRo {

    private Long tagId;

    private String hashTag;

}
