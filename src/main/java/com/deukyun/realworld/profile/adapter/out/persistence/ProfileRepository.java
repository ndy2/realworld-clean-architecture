package com.deukyun.realworld.profile.adapter.out.persistence;

import com.deukyun.realworld.profile.application.port.out.ProfileOutResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

interface ProfileRepository extends JpaRepository<ProfileJpaEntity, Long> {

    @Query("select new com.deukyun.realworld.profile.application.port.out.ProfileOutResponse(p.username, p.bio, p.image) " +
            "from ProfileJpaEntity p " +
            "where p.userId = :userId")
    Optional<ProfileOutResponse> findByUserIdProjection(long userId);
}
