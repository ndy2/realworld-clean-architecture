package com.deukyun.realworld.follow.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FollowJpaRepository extends JpaRepository<FollowJpaEntity, Long> {

    Optional<FollowJpaEntity> findByFollowerIdEqualsAndFolloweeIdEquals(long followeeId, long followerId);
}
