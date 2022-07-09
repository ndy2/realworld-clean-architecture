package com.deukyun.realworld.follow.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowJpaRepository extends JpaRepository<FollowJpaEntity, Long> {

    boolean existsByFollowerIdEqualsAndFolloweeIdEquals(long followeeId, long followerId);
}
