package com.bamboo.bamboodgsw.domain.user.entity;

import com.bamboo.bamboodgsw.domain.post.entity.Post;
import com.bamboo.bamboodgsw.domain.user.type.UserRole;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    private String id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String profileImage;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserRole userRole;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();
    public void addPost(Post post) {
        post.modifyUser(this);
        this.posts.add(post);
    }

    @Builder
    public User(String name, String profileImage, UserRole userRole) {
        this.name = name;
        this.profileImage = profileImage;
        this.userRole = userRole;
    }
}
