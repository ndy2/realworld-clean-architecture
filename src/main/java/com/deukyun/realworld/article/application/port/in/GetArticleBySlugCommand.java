package com.deukyun.realworld.article.application.port.in;

import lombok.Value;

@Value
public class GetArticleBySlugCommand {

    String slug;


    Long userId;
}
