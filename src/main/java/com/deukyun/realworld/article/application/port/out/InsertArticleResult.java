package com.deukyun.realworld.article.application.port.out;

import lombok.Value;

import java.time.LocalDateTime;

@Value
public class InsertArticleResult {

    LocalDateTime createdAt;
}
