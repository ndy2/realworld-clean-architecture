package com.deukyun.realworld.article.application.port.out;

import lombok.Value;

@Value
public class FindFeedArticleCommand {

    long limit;
    long offset;
    long userId;
}
