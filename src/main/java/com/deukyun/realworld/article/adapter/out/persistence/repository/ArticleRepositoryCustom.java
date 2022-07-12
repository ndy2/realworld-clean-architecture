package com.deukyun.realworld.article.adapter.out.persistence.repository;

import com.deukyun.realworld.article.adapter.out.persistence.ArticleJpaEntity;

import java.util.List;
import java.util.Optional;

interface ArticleRepositoryCustom {

    List<ArticleJpaEntity> searchArticle(ArticleSearchCond cond, long offset, long limit);

    List<ArticleJpaEntity> feedArticles(long userId, long offset, long limit);

    Optional<ArticleJpaEntity> findBySlug(String slug);
}
