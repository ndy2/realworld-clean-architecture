package com.deukyun.realworld.profile.adapter.out.persistence;

import com.deukyun.realworld.common.base.BaseIdEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "profile")
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
class ProfileJpaEntity extends BaseIdEntity {

    private String username;
    private String bio;
    private String image;
}
