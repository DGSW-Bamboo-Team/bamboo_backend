package com.bamboo.bamboodgsw.domain.upload.presentation.ro;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @AllArgsConstructor
@Builder
public class PostAttachmentRo {

    private Long attachmentId;

    private String originFilePath;

    private String saveFilePath;

}
