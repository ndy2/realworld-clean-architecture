package com.deukyun.realworld.article.domain;

import lombok.EqualsAndHashCode;
import lombok.Value;

@EqualsAndHashCode(of = "id")
public class Article {

    private ArticleId id;

    private final String title;

    private final String description;

    private final String body;

    private final Tags tags;

    public Article(String title, String description, String body, Tags tags) {
        this.title = title;
        this.description = description;
        this.body = body;
        this.tags = tags;
    }

    @Value
    public static class ArticleId {
        Long value;
    }
}
