package com.deukyun.realworld.favorite.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface FavoriteRepository extends JpaRepository<FavoriteJpaEntity, Long> {

    Optional<FavoriteJpaEntity> findByUserIdEqualsAndArticleIdEquals(long userId, long article);
}
