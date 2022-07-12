package com.deukyun.realworld.article.application.port.out.dto.query;

import lombok.Value;

@Value
public class FindFeedArticleQuery {

    long limit;
    long offset;
    long userId;
}
