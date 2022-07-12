package com.deukyun.realworld.article.application.service;

import com.deukyun.realworld.article.application.port.in.AuthorResult;
import com.deukyun.realworld.article.application.port.in.CreateArticleCommand;
import com.deukyun.realworld.article.application.port.in.CreateArticleResult;
import com.deukyun.realworld.article.application.port.in.CreateArticleUseCase;
import com.deukyun.realworld.article.application.port.out.InsertArticleCommand;
import com.deukyun.realworld.article.application.port.out.InsertArticlePort;
import com.deukyun.realworld.article.application.port.out.InsertArticleResult;
import com.deukyun.realworld.common.component.UseCase;
import com.deukyun.realworld.profile.application.port.out.FindProfileByUserIdPort;
import com.deukyun.realworld.profile.application.port.out.FindProfileByUserIdResult;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
class CreateArticleService implements CreateArticleUseCase {

    private final InsertArticlePort insertArticlePort;
    private final FindProfileByUserIdPort findProfileByUserIdPort;

    @Override
    public CreateArticleResult createArticle(CreateArticleCommand createArticleCommand) {

        String slug = createSlug(createArticleCommand);

        FindProfileByUserIdResult authorProfile
                = findProfileByUserIdPort.findByUserId(createArticleCommand.getAuthorUserId());

        InsertArticleResult articleResult = insertArticlePort.insertArticle(
                new InsertArticleCommand(
                        slug,
                        createArticleCommand.getTitle(),
                        createArticleCommand.getDescription(),
                        createArticleCommand.getBody(),
                        createArticleCommand.getTagList(),
                        authorProfile.getId()
                )
        );

        return new CreateArticleResult(
                slug,
                articleResult.getCreatedAt(),
                new AuthorResult(
                        authorProfile.getUsername(),
                        authorProfile.getBio(),
                        authorProfile.getImage(),
                        //자신의 아티클 이므로 팔로잉 여부 : false
                        false
                )
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
