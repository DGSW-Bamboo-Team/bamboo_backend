package com.bamboo.bamboodgsw.domain.post.repository;

import com.bamboo.bamboodgsw.domain.post.entity.Post;
import com.bamboo.bamboodgsw.domain.post.entity.PostTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostTagRepository extends JpaRepository<PostTag, Long> {

    List<PostTag> findAllByPost(Post post);

}
