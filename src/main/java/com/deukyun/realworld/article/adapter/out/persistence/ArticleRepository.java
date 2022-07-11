package com.deukyun.realworld.article.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

interface ArticleRepository extends JpaRepository<ArticleJpaEntity, Long> {

    //Tag 와 Author 정보 fetch
    @Query("select a " +
            "from ArticleJpaEntity a " +
            "join fetch a.articleTags at " +
            "join fetch at.tag " +
            "join fetch a.authorProfile ap " +
            "where a.slug = :slug")
    Optional<ArticleJpaEntity> findBySlug(String slug);
}
