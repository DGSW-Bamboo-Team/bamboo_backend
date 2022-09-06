package com.bamboo.bamboodgsw.domain.upload.entity;

import com.bamboo.bamboodgsw.domain.post.entity.Post;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostAttachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attachmentId;

    @ManyToOne
    @JoinColumn(name = "fk_post_id")
    private Post post;
    public void modifyPost(Post post) {
        this.post = post;
    }

    @Column(nullable = false)
    private String originFileName;

    @Column(nullable = false)
    private String saveFilePath;

    @CreationTimestamp
    private LocalDateTime createAt;

    @Builder
    public PostAttachment(Post post, String originFileName, String saveFilePath) {
        this.post = post;
        this.originFileName = originFileName;
        this.saveFilePath = saveFilePath;
    }
}
