package com.deukyun.realworld.article.application.port.out;

import lombok.Value;

@Value
public class FindArticlesByFieldsQuery {

    //fields
    String tag;
    String author;
    String favorited;

    //paging
    long limit;
    long offset;
}
