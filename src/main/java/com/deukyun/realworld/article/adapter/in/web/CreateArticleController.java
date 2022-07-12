package com.deukyun.realworld.article.adapter.in.web;

import com.deukyun.realworld.article.application.port.in.AuthorResult;
import com.deukyun.realworld.article.application.port.in.CreateArticleCommand;
import com.deukyun.realworld.article.application.port.in.CreateArticleResult;
import com.deukyun.realworld.article.application.port.in.CreateArticleUseCase;
import com.deukyun.realworld.article.domain.Tags;
import com.deukyun.realworld.common.SecurityUser;
import com.deukyun.realworld.user.domain.User.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;


@RequiredArgsConstructor
@RestController
public class CreateArticleController {

    private final CreateArticleUseCase createArticleUseCase;

    @PostMapping("/api/articles")
    public CreateArticleResponse createArticle(
            @AuthenticationPrincipal SecurityUser securityUser,
            @RequestBody CreateArticleRequest createArticleRequest
    ) {
        UserId userId = securityUser.getUserId();
        String title = createArticleRequest.getTitle();
        String description = createArticleRequest.getDescription();
        String body = createArticleRequest.getBody();
        Tags tags = createArticleRequest.getTags();

        CreateArticleResult articleResult
                = createArticleUseCase.createArticle(new CreateArticleCommand(title, description, body, tags, userId));

        String slug = articleResult.getSlug();
        LocalDateTime createdAt = articleResult.getCreatedAt();
        AuthorResult authorResult = articleResult.getAuthorResult();

        // 응답 말기
        CreateArticleResponse response = new CreateArticleResponse(slug, title, description, body, tags, createdAt);
        response.addAuthorResponse(
                authorResult.getUsername(),
                authorResult.getBio(),
                authorResult.getImage()
        );
        return response;
    }

}
