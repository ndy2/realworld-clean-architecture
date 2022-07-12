package com.deukyun.realworld.article.adapter.out.persistence.repository;

import lombok.Value;

@Value
public class ArticleSearchCond {

    String tag;
    String author;
    String favorited;
}
