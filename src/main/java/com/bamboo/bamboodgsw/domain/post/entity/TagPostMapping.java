package com.bamboo.bamboodgsw.domain.post.entity;

import lombok.*;

import javax.persistence.*;

@Entity @Builder
@AllArgsConstructor
@Getter @NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TagPostMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mapId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_post_id")
    private Post post;
    public void setPost(Post post) {
        this.post = post;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_tag_id")
    private Tag tag;
    public void setTag(Tag tag) {
        this.tag = tag;
    }


}
