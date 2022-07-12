package com.deukyun.realworld.article.application.port.in;

import lombok.Value;

@Value
public class GetArticleBySlugQuery {

    String slug;


    Long userId;
}
