package com.deukyun.realworld.favorite.application.port.in;

import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;

@Value
public class FavoriteArticleResult {

    String slug;
    String title;
    String description;
    String body;
    List<String> tagList;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    boolean favorited;
    long favoritesCount;
    FavoriteAuthorResult author;
}
