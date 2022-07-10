package com.deukyun.realworld.article.adapter.in.web;

import com.deukyun.realworld.article.application.port.in.CreateArticlePort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.deukyun.realworld.infrastructure.security.jwt.JwtAuthenticationToken.JwtAuthentication;

@RequiredArgsConstructor
@RestController
public class CreateArticleController {

    private final CreateArticlePort createArticlePort;

    @PostMapping("/api/articles")
    public CreateArticleResponse createArticle(
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication,
            CreateArticleRequest createArticleRequest) {


        return null;
    }

}
