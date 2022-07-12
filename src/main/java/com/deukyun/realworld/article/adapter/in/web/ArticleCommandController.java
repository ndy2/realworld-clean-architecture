package com.deukyun.realworld.article.adapter.in.web;

import com.deukyun.realworld.article.adapter.in.dto.command.CreateArticleRequest;
import com.deukyun.realworld.article.adapter.in.dto.command.CreateArticleResponse;
import com.deukyun.realworld.article.application.port.in.AuthorResult;
import com.deukyun.realworld.article.application.port.in.CreateArticleCommand;
import com.deukyun.realworld.article.application.port.in.CreateArticleResult;
import com.deukyun.realworld.article.application.port.in.CreateArticleUseCase;
import com.deukyun.realworld.common.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;


@RequiredArgsConstructor
@RestController
public class ArticleCommandController {

    private final CreateArticleUseCase createArticleUseCase;

    @PostMapping("/api/articles")
    public CreateArticleResponse createArticle(
            @AuthenticationPrincipal SecurityUser securityUser,
            @RequestBody CreateArticleRequest createArticleRequest
    ) {
        String title = createArticleRequest.getTitle();
        String description = createArticleRequest.getDescription();
        String body = createArticleRequest.getBody();
        List<String> tagList = createArticleRequest.getTagList();
        long userId = securityUser.getUserId();

        CreateArticleResult articleResult
                = createArticleUseCase.createArticle(new CreateArticleCommand(title, description, body, tagList, userId));

        String slug = articleResult.getSlug();
        LocalDateTime createdAt = articleResult.getCreatedAt();
        AuthorResult authorResult = articleResult.getAuthorResult();

        CreateArticleResponse response = new CreateArticleResponse(slug, title, description, body, tagList, createdAt);
        response.addAuthorResponse(
                authorResult.getUsername(),
                authorResult.getBio(),
                authorResult.getImage()
        );
        return response;
    }


}
