package com.deukyun.realworld.article.application.port.in;

import lombok.Value;

@Value
public class ListArticlesCommand {

    String tag;
    String author;
    String favorited;
    long limit;
    long offset;
}
