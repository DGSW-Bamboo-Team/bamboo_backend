package com.bamboo.bamboodgsw.domain.upload.repository;

import com.bamboo.bamboodgsw.domain.upload.entity.PostAttachment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostAttachmentRepository extends JpaRepository<PostAttachment, Long> {
}
