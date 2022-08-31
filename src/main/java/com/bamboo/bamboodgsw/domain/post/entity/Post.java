package com.bamboo.bamboodgsw.domain.post.entity;

import com.bamboo.bamboodgsw.domain.post.type.PostStatus;
import com.bamboo.bamboodgsw.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @ManyToOne
    @JoinColumn(name = "fk_user_id")
    private User userId;

    @Column(nullable = false)
    private String content;

    @CreationTimestamp
    private LocalDateTime createAt;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private PostStatus status;

    @Builder
    public Post(Long postId,
                User userId,
                String content,
                PostStatus status) {
        this.postId = postId;
        this.userId = userId;
        this.content = content;
        this.status = status;
    }
}
