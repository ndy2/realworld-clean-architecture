package com.deukyun.realworld.article.application.port.in;

public interface GetArticleBySlugQuery {

    ArticleResult getArticleBySlug(String slug);
}
