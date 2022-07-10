package com.deukyun.realworld.article.application.service;

import com.deukyun.realworld.article.application.port.in.CreateArticleCommand;
import com.deukyun.realworld.article.application.port.in.CreateArticleResult;
import com.deukyun.realworld.article.application.port.in.CreateArticleUseCase;
import com.deukyun.realworld.article.application.port.out.InsertArticleCommand;
import com.deukyun.realworld.article.application.port.out.InsertArticlePort;
import com.deukyun.realworld.article.application.port.out.InsertArticleResult;
import com.deukyun.realworld.common.component.UseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
class CreateArticleService implements CreateArticleUseCase {

    private final InsertArticlePort insertArticlePort;

    @Override
    public CreateArticleResult createArticle(CreateArticleCommand createArticleCommand) {

        String slug = createSlug(createArticleCommand);
        long authorUserId = createArticleCommand.getAuthorUserId();
        long authorProfileId = authorUserId + 1;

        InsertArticleResult articleResult = insertArticlePort.insertArticle(
                new InsertArticleCommand(
                        slug,
                        createArticleCommand.getTitle(),
                        createArticleCommand.getDescription(),
                        createArticleCommand.getBody(),
                        createArticleCommand.getTagList(),
                        authorProfileId
                )
        );

        return new CreateArticleResult(
                slug,
                articleResult.getCreatedAt(),
                null
        );
    }

    /**
     * 타이틀을 이요해 Slug 를 만듬
     */
    private String createSlug(CreateArticleCommand createArticleCommand) {

        String title = createArticleCommand.getTitle();

        return String.join("-", title.toLowerCase().split(" "));
    }
}