package com.deukyun.realworld.article.adapter.in.web;

import com.deukyun.realworld.article.application.port.in.CreateArticleCommand;
import com.deukyun.realworld.article.application.port.in.CreateArticleResult;
import com.deukyun.realworld.article.application.port.in.CreateArticleUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

import static com.deukyun.realworld.infrastructure.security.jwt.JwtAuthenticationToken.JwtAuthentication;

@RequiredArgsConstructor
@RestController
public class CreateArticleController {

    private final CreateArticleUseCase createArticleUseCase;

    @PostMapping("/api/articles")
    public CreateArticleResponse createArticle(
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication,
            CreateArticleRequest createArticleRequest) {

        String title = createArticleRequest.getTitle();
        String description = createArticleRequest.getDescription();
        String body = createArticleRequest.getBody();
        List<String> tagList = createArticleRequest.getTagList();
        long userId = jwtAuthentication.getUserId();

        CreateArticleResult articleResult
                = createArticleUseCase.createArticle(new CreateArticleCommand(title, description, body, tagList, userId));

        String slug = articleResult.getSlug();
        LocalDateTime createdAt = articleResult.getCreatedAt();

        //TODO - 페이보릿 기능
        return new CreateArticleResponse(slug, title, description, body, tagList, createdAt, false, 0);
    }

}
