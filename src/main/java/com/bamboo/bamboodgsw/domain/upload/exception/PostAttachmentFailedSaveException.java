package com.bamboo.bamboodgsw.domain.post.exception;

import com.bamboo.bamboodgsw.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class PostAttachmentFailedSaveException extends BusinessException {

    public static final PostAttachmentFailedSaveException EXCEPTION = new PostAttachmentFailedSaveException();

    private PostAttachmentFailedSaveException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "파일을 저장하지 못하였습니다");
    }
}
