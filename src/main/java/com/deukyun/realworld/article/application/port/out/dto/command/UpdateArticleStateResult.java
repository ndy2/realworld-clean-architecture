package com.deukyun.realworld.article.application.port.out.dto.command;

import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Value
public class UpdateArticleStateResult {

    long id;
    String slug;
    String title;
    String description;
    String body;
    List<String> tagList;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    CommandAuthorResult author;
}
