package com.deukyun.realworld.article.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface ArticleRepository extends JpaRepository<ArticleJpaEntity, Long> {

    Optional<ArticleJpaEntity> findBySlug(String slug);
}
