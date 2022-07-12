package com.deukyun.realworld.article.application.service;

import com.deukyun.realworld.article.application.port.in.DeleteArticleUseCase;
import com.deukyun.realworld.article.application.port.out.DeleteArticlePort;
import com.deukyun.realworld.common.component.UseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class DeleteArticleService implements DeleteArticleUseCase {

    private final DeleteArticlePort deleteArticlePort;

    @Override
    public void deleteArticle(long userId, String slug) {

        deleteArticlePort.deleteArticle(userId, slug);
    }
}
