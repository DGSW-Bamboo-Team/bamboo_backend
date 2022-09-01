package com.bamboo.bamboodgsw.domain.post.repository;

import com.bamboo.bamboodgsw.domain.post.entity.Post;
import com.bamboo.bamboodgsw.domain.post.type.PostStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findAllByStatus(PostStatus status, Pageable pageable);

}
