package com.deukyun.realworld.article.adapter.out.persistence;

import java.util.List;

interface ArticleRepositoryCustom {

    List<ArticleJpaEntity> searchArticle(ArticleSearchCond cond, long offset, long limit);
}
