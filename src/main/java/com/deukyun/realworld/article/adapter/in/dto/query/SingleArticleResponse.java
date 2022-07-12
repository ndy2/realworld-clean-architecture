package com.deukyun.realworld.article.adapter.in.dto.query;

import lombok.Getter;

@Getter
public class SingleArticleResponse {

    public final ArticleResponse article;

    public SingleArticleResponse(ArticleResponse article) {
        this.article = article;
    }
}
