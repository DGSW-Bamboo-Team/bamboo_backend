package com.bamboo.bamboodgsw.domain.post.exception;

import com.bamboo.bamboodgsw.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class TagNotFoundException extends BusinessException {

    public static TagNotFoundException EXCEPTION = new TagNotFoundException();

    private TagNotFoundException() {
        super(HttpStatus.NOT_FOUND, "해시태그를 찾을 수 없습니다");
    }
}
