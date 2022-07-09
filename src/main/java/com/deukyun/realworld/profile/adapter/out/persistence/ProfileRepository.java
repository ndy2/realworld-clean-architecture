package com.deukyun.realworld.profile.adapter.out.persistence;

import com.deukyun.realworld.profile.application.port.out.FindProfileResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

interface ProfileRepository extends JpaRepository<ProfileJpaEntity, Long> {

    @Query("select new com.deukyun.realworld.profile.application.port.out.FindProfileResult(p.username, p.bio, p.image) " +
            "from ProfileJpaEntity p " +
            "where p.userId = :userId")
    Optional<FindProfileResult> findByUserIdProjection(long userId);
}
