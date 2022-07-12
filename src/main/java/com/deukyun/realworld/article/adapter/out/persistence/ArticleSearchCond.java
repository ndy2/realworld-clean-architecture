package com.deukyun.realworld.article.adapter.out.persistence;

import lombok.Value;

@Value
class ArticleSearchCond {

    String tag;
    String author;
    String favorited;
}
