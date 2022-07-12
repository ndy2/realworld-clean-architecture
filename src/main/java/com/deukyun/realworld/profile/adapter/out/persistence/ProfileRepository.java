package com.deukyun.realworld.profile.adapter.out.persistence;

import com.deukyun.realworld.profile.application.port.out.dto.query.FindProfileByUsernameResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

interface ProfileRepository extends JpaRepository<ProfileJpaEntity, Long> {

    @Query("select p " +
            "from ProfileJpaEntity p " +
            "join fetch p.user u " +
            "where u.id = :userId")
    Optional<ProfileJpaEntity> findByUserIdProjection(long userId);

    Optional<ProfileJpaEntity> findByUserId(long userId);

    @Query("select p.id " +
            "from ProfileJpaEntity p " +
            "where p.user.id = :userId")
    Optional<Long> findIdByUserId(long userId);

    @Query("select new com.deukyun.realworld.profile.application.port.out.dto.query.FindProfileByUsernameResult(p.id, p.username, p.bio, p.image) " +
            "from ProfileJpaEntity p " +
            "where p.username = :username")
    Optional<FindProfileByUsernameResult> findByUsernameProjection(String username);
}
