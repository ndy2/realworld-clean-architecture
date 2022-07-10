package com.deukyun.realworld.article.application.port.in;

import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Value
public class CreateArticleResult {

    String slug;
    String title;
    String description;
    String body;
    List<String> tagList;
    LocalDateTime createdAt;
    AuthorResult authorResult;
}
