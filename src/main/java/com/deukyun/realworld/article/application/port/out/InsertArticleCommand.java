package com.deukyun.realworld.article.application.port.out;

import com.deukyun.realworld.article.domain.Tags;
import com.deukyun.realworld.profile.domain.Profile.ProfileId;
import lombok.Value;

@Value
public class InsertArticleCommand {

    String slug;
    String title;
    String description;
    String body;
    Tags tags;
    ProfileId authorProfileId;
}
