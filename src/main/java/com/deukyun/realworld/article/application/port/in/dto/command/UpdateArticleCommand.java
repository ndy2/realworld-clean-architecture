package com.deukyun.realworld.article.application.port.in.dto.command;

import lombok.Value;

@Value
public class UpdateArticleCommand {

    long userId;
    String title;
    String slug;
    String description;
    String body;
}
