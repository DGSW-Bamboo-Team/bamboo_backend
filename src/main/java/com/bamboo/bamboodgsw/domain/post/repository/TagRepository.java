package com.bamboo.bamboodgsw.domain.post.repository;

import com.bamboo.bamboodgsw.domain.post.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

    Optional<Tag> findByHashTag(String hashTag);

    boolean existsByHashTag(String hashTag);

    Tag getByHashTag(String hashTag);

}
