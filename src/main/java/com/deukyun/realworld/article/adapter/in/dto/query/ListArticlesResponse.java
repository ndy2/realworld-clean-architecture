package com.deukyun.realworld.article.adapter.in.dto.query;

import lombok.Getter;

import java.util.List;

@Getter
public class ListArticlesResponse {

    private final List<ArticleResponse> articles;
    private final int articleCount;

    public ListArticlesResponse(List<ArticleResponse> articles) {
        this.articles = articles;
        this.articleCount = articles.size();
    }
}
