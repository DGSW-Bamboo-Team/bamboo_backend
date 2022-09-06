package com.bamboo.bamboodgsw.domain.post.exception;

import com.bamboo.bamboodgsw.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class PostAttachmentNotFoundException extends BusinessException {

    public static final PostAttachmentNotFoundException EXCEPTION =
            new PostAttachmentNotFoundException();

    private PostAttachmentNotFoundException() {
        super(HttpStatus.NOT_FOUND, "첨부파일을 찾을 수 없습니다");
    }
}
