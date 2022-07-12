package com.deukyun.realworld.favorite.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

interface FavoriteRepository extends JpaRepository<FavoriteJpaEntity, Long> {

    Optional<FavoriteJpaEntity> findByUserIdEqualsAndArticleIdEquals(long userId, long article);

    long countByArticleId(long articleId);

    @Query("select f.articleId from FavoriteJpaEntity f where f.userId = :userId")
    List<Long> findFavoriteArticleIdsByUserId(Long userId);
}
