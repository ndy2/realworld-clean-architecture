package com.deukyun.realworld.follow.adapter.out.persistence;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static lombok.AccessLevel.PROTECTED;

@Table(name = "follow")
@Entity
@NoArgsConstructor(access = PROTECTED)
public class FollowJpaEntity {

    @Id
    @GeneratedValue
    private Long id;
    private long followerId;
    private long followeeId;

    public FollowJpaEntity(long followerId, long followeeId) {
        this.followerId = followerId;
        this.followeeId = followeeId;
    }

    public Long getId() {
        return id;
    }
}
