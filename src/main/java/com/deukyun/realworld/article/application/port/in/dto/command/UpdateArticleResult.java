package com.deukyun.realworld.article.application.port.in.dto.command;

import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Value
public class UpdateArticleResult {

    String slug;
    String title;
    String description;
    String body;
    List<String> tagList;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    boolean favorited;
    long favoritesCount;
    AuthorResult authorResult;
}
