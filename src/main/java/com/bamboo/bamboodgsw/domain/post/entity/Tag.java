package com.bamboo.bamboodgsw.domain.post.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tagId;

    @Column(nullable = false)
    private String hashTag;

    @Builder
    public Tag(String hashTag) {
        this.hashTag = hashTag;
    }
}
