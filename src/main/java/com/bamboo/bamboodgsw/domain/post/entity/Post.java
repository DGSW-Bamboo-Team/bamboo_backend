package com.bamboo.bamboodgsw.domain.post.entity;

import com.bamboo.bamboodgsw.domain.post.type.PostStatus;
import com.bamboo.bamboodgsw.domain.user.entity.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @ManyToOne
    @JoinColumn(name = "fk_user_id")
    private User user;
    public void modifyUser(User user) {
        this.user = user;
    }

    @Column(nullable = false)
    @Lob
    private String content;

    @CreationTimestamp
    private LocalDateTime createAt;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private PostStatus status;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostTag> postTagList;
    public void addMappings(PostTag mapping) {
        mapping.setPost(this);
        this.postTagList.add(mapping);
    }

    @Builder
    public Post(User user, String content, PostStatus status, List<PostTag> postTagList) {
        this.user = user;
        this.content = content;
        this.status = status;
        this.postTagList = postTagList;
    }
}
