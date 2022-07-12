package com.deukyun.realworld.follow.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<FollowJpaEntity, Long> {

    Optional<FollowJpaEntity> findByFollowerIdEqualsAndFolloweeIdEquals(long followeeId, long followerId);

    @Query("select f.followeeId from FollowJpaEntity f where f.followerId = :followerId")
    List<Long> findFolloweeIdsByFollowerId(long followerId);
}
