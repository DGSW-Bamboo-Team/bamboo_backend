package com.bamboo.bamboodgsw.domain.user.entity;

import com.bamboo.bamboodgsw.domain.user.type.UserRole;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

    @Builder
    public User(String name, String profileImage, UserRole userRole) {
        this.name = name;
        this.profileImage = profileImage;
        this.userRole = userRole;
    }
}
