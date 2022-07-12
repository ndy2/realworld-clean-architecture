package com.deukyun.realworld.article.application.port.in.dto.query;

import lombok.Value;

@Value
public class ListArticlesQuery {

    String tag;
    String author;
    String favorited;
    long limit;
    long offset;

    // nullable - 미 인증 사용자의 경우 null
    Long userId;
}
