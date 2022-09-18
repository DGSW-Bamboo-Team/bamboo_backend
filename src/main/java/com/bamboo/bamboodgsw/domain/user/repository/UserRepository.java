package com.bamboo.bamboodgsw.domain.user.repository;

import com.bamboo.bamboodgsw.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {


}
