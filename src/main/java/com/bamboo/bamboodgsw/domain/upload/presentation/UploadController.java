package com.bamboo.bamboodgsw.domain.upload.presentation;

import com.bamboo.bamboodgsw.domain.upload.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/upload")
public class UploadController {

    private final UploadService uploadService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/")
    public Long uploadAttachment(
            @RequestParam MultipartFile request
    ) {
        return uploadService.uploadAttachment(request);
    }

    @GetMapping(value = "/{attachment-id}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> getAttachment(
            @PathVariable("attachment-id") Long attachmentId
    ) {
        return uploadService.getAttachment(attachmentId);
    }

}
