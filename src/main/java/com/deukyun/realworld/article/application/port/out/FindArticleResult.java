package com.deukyun.realworld.article.application.port.out;

import com.deukyun.realworld.article.domain.Article.ArticleId;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Value
public class FindArticleResult {

    ArticleId id;
    String slug;
    String title;
    String description;
    String body;
    List<String> tagList;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    FindAuthorResult author;

}
