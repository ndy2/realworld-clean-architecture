package com.deukyun.realworld.article.application.port.in;

import com.deukyun.realworld.article.domain.Tags;
import lombok.Value;

import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static org.springframework.util.StringUtils.hasText;

@Value
public class CreateArticleCommand {

    String title;
    String description;
    String body;
    Tags tags;

    public CreateArticleCommand(String title, String description, String body, List<String> tagList) {
        checkArgument(hasText(title), "title is not provided");
        checkArgument(hasText(description), "description is not provided");
        checkArgument(hasText(body), "body is not provided");

        this.title = title;
        this.description = description;
        this.body = body;
        this.tags = tagList == null ? null : new Tags(tagList);
    }
}
