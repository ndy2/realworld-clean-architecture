package com.deukyun.realworld.article.application.port.out;

import lombok.Value;

import java.util.List;

@Value
public class InsertArticleCommand {

    String slug;
    String title;
    String description;
    String body;
    List<String> tags;
    long authorProfileId;
}
