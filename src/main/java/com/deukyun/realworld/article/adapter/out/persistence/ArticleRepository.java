package com.deukyun.realworld.article.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface ArticleRepository extends JpaRepository<ArticleJpaEntity, Long> {
}
