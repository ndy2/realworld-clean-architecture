package com.deukyun.realworld.article.adapter.in.web;

import com.deukyun.realworld.article.adapter.in.dto.command.CreateArticleRequest;
import com.deukyun.realworld.article.adapter.in.dto.command.CreateArticleResponse;
import com.deukyun.realworld.article.adapter.in.dto.command.UpdateArticleRequest;
import com.deukyun.realworld.article.adapter.in.dto.command.UpdateArticleResponse;
import com.deukyun.realworld.article.application.port.in.CreateArticleUseCase;
import com.deukyun.realworld.article.application.port.in.DeleteArticleUseCase;
import com.deukyun.realworld.article.application.port.in.UpdateArticleUseCase;
import com.deukyun.realworld.article.application.port.in.dto.command.*;
import com.deukyun.realworld.common.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@RequiredArgsConstructor
@RestController
public class ArticleCommandController {

    private final CreateArticleUseCase createArticleUseCase;
    private final UpdateArticleUseCase updateArticleUseCase;
    private final DeleteArticleUseCase deleteArticleUseCase;

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

    @PatchMapping("/api/articles/{slug}")
    public UpdateArticleResponse updateArticle(
            @AuthenticationPrincipal SecurityUser securityUser,
            @RequestBody UpdateArticleRequest updateArticleRequest,
            @PathVariable String slug
    ) {

        UpdateArticleResult article = updateArticleUseCase.updateArticle(
                new UpdateArticleCommand(
                        securityUser.getUserId(),
                        updateArticleRequest.getTitle(),
                        slug,
                        updateArticleRequest.getDescription(),
                        updateArticleRequest.getBody()
                )
        );
        AuthorResult author = article.getAuthorResult();

        UpdateArticleResponse response = new UpdateArticleResponse(
                article.getSlug(),
                article.getTitle(),
                article.getDescription(),
                article.getBody(),
                article.getTagList(),
                article.getCreatedAt(),
                article.getUpdatedAt(),
                article.isFavorited(),
                article.getFavoritesCount()
        );
        response.addAuthorResponse(
                author.getUsername(),
                author.getBio(),
                author.getImage(),
                author.isFollowing()
        );
        return response;
    }

    @DeleteMapping("/api/articles/{slug}")
    public ResponseEntity<?> deleteArticle(
            @AuthenticationPrincipal SecurityUser securityUser,
            @PathVariable String slug
    ) {
        deleteArticleUseCase.deleteArticle(securityUser.getUserId(), slug);

        return ResponseEntity.noContent().build();
    }

}
