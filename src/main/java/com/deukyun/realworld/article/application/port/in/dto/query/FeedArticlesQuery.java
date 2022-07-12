package com.deukyun.realworld.article.application.port.in.dto.query;

import lombok.Value;

@Value
public class FeedArticlesQuery {

    long limit;
    long offset;
    long userId;
}
