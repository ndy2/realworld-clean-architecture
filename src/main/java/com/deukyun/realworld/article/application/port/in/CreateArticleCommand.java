package com.deukyun.realworld.article.application.port.in;

import com.deukyun.realworld.article.domain.Tags;
import lombok.Value;

import java.util.Collections;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static org.springframework.util.StringUtils.hasText;

@Value
public class CreateArticleCommand {

    String title;
    String description;
    String body;
    Tags tags;
    long authorUserId;

    public CreateArticleCommand(String title, String description, String body, List<String> tagList, long authorUserId) {
        checkArgument(hasText(title), "title is not provided");
        checkArgument(hasText(description), "description is not provided");
        checkArgument(hasText(body), "body is not provided");

        this.title = title;
        this.description = description;
        this.body = body;
        this.tags = tagList == null ? new Tags(Collections.emptyList()) : new Tags(tagList);
        this.authorUserId = authorUserId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getBody() {
        return body;
    }

    public List<String> getTagList() {
        return tags.getTagList();
    }

    public long getAuthorUserId() {
        return authorUserId;
    }
}
