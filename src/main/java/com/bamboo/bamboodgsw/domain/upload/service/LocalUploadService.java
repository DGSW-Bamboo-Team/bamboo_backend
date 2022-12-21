package com.bamboo.bamboodgsw.domain.upload.service;

import com.bamboo.bamboodgsw.domain.upload.entity.PostAttachment;
import com.bamboo.bamboodgsw.domain.upload.exception.PostAttachmentFailedSaveException;
import com.bamboo.bamboodgsw.domain.upload.exception.PostAttachmentNotFoundException;
import com.bamboo.bamboodgsw.domain.upload.presentation.ro.PostAttachmentRo;
import com.bamboo.bamboodgsw.domain.upload.repository.PostAttachmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LocalUploadService implements UploadService{

    private final PostAttachmentRepository postAttachmentRepository;

    public final String path = "../../../resources/static";

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public PostAttachmentRo uploadAttachment(MultipartFile file) {
        try {
            if(!file.isEmpty()) {
                String uuid = UUID.randomUUID().toString();
                String originFileName = file.getOriginalFilename();
                String fileName = originFileName.substring(originFileName.lastIndexOf("\\") + 1);
                String saveFileName = path + File.separator + uuid + "_" + fileName;
                Path savePath = Paths.get(saveFileName);

                file.transferTo(savePath);

                PostAttachment attachment = PostAttachment.builder()
                        .originFileName(originFileName)
                        .saveFilePath(savePath.toFile().getCanonicalPath())
                        .build();
                attachment = postAttachmentRepository.save(attachment);

                return PostAttachmentRo.builder()
                        .attachmentId(attachment.getAttachmentId())
                        .originFilePath(attachment.getOriginFileName())
                        .saveFilePath(attachment.getSaveFilePath())
                        .build();
            } else {
                throw PostAttachmentNotFoundException.EXCEPTION;
            }
        } catch (IOException e) {
            throw PostAttachmentFailedSaveException.EXCEPTION;
        }
    }

}
