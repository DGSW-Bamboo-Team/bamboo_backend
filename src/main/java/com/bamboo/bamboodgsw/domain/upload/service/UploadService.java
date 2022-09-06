package com.bamboo.bamboodgsw.domain.upload.service;

import com.bamboo.bamboodgsw.domain.upload.entity.PostAttachment;
import com.bamboo.bamboodgsw.domain.upload.exception.PostAttachmentFailedSaveException;
import com.bamboo.bamboodgsw.domain.upload.exception.PostAttachmentNotFoundException;
import com.bamboo.bamboodgsw.domain.upload.repository.PostAttachmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UploadService {

    private final PostAttachmentRepository postAttachmentRepository;

    @Transactional(rollbackFor = RuntimeException.class)
    public Long uploadAttachment(MultipartFile request) {
        try {
            PostAttachment attachment = PostAttachment.builder()
                    .originFileName(request.getOriginalFilename())
                    .data(request.getBytes())
                    .build();

            return postAttachmentRepository.save(attachment).getAttachmentId();
        } catch (IOException e) {
            throw PostAttachmentFailedSaveException.EXCEPTION;
        }
    }

    @Transactional(readOnly = true)
    public ResponseEntity<byte[]> getAttachment(Long attachmentId) {
        PostAttachment postAttachment = postAttachmentRepository.findById(attachmentId)
                .orElseThrow(() -> {
                    throw PostAttachmentNotFoundException.EXCEPTION;
                });

        String headerData = String.format("attachment; filename=\"%s\";", postAttachment.getOriginFileName());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, headerData)
                .body(postAttachment.getData());
    }

}
