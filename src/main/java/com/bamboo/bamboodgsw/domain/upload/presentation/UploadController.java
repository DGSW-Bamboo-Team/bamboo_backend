package com.bamboo.bamboodgsw.domain.upload.presentation;

import com.bamboo.bamboodgsw.domain.upload.presentation.ro.PostAttachmentRo;
import com.bamboo.bamboodgsw.domain.upload.service.LocalUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/upload")
public class UploadController {

    private final LocalUploadService uploadService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public PostAttachmentRo uploadAttachment(
            @RequestParam MultipartFile request
    ) {
        return uploadService.uploadAttachment(request);
    }

}
