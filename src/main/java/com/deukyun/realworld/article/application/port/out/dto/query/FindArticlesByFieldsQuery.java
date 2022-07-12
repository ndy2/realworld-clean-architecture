package com.deukyun.realworld.article.application.port.out.dto.query;

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
