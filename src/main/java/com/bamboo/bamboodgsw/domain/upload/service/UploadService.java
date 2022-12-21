package com.bamboo.bamboodgsw.domain.upload.service;

import com.bamboo.bamboodgsw.domain.upload.presentation.ro.PostAttachmentRo;
import org.springframework.web.multipart.MultipartFile;

public interface UploadService {

    PostAttachmentRo uploadAttachment(MultipartFile file);

}
