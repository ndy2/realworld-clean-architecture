package com.deukyun.realworld.profile.adapter.out.persistence;

import com.deukyun.realworld.user.adapter.out.persistence.UserJpaEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Optional;

import static javax.persistence.FetchType.LAZY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "profile")
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class ProfileJpaEntity {

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String bio;
    private String image;

    @OneToOne(fetch = LAZY)
    private UserJpaEntity user;

    /**
     * 회원 가입 할 때 사용 - username, userId 만 필요함
     */
    public ProfileJpaEntity(String username, long userId) {
        this.username = username;
        this.user = UserJpaEntity.withId(userId);
    }

    public void update(String updateUsername, String updateBio, String updateImage) {

        Optional.ofNullable(updateUsername).ifPresent(wrapper -> this.username = wrapper);
        Optional.ofNullable(updateBio).ifPresent(wrapper -> this.bio = wrapper);
        Optional.ofNullable(updateImage).ifPresent(wrapper -> this.image = wrapper);
    }

    public String getUsername() {
        return username;
    }

    public String getBio() {
        return bio;
    }

    public String getImage() {
        return image;
    }

    public String getUserEmail() {
        return user.getEmail();
    }
}
