package com.deukyun.realworld.article.application.port.in.dto.command;

import com.deukyun.realworld.article.application.port.in.dto.query.AuthorResult;
import lombok.Value;

import java.time.LocalDateTime;

@Value
public class CreateArticleResult {

    String slug;
    LocalDateTime createdAt;
    AuthorResult authorResult;
}
