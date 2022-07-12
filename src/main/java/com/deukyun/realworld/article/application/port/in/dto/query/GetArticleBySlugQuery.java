package com.deukyun.realworld.article.application.port.in.dto.query;

import lombok.Value;

@Value
public class GetArticleBySlugQuery {

    String slug;

    // nullable - 미 인증 사용자의 경우 null
    Long userId;
}
