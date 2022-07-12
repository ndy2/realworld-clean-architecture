package com.deukyun.realworld.article.application.port.out.dto.command;

import lombok.Value;

@Value
public class UpdateArticleStateCommand {

    long userId;
    String title;
    String origSlug;
    String updateSlug;
    String description;
    String body;
}
