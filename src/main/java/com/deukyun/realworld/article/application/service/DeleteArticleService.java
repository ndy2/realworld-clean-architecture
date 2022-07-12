package com.deukyun.realworld.article.application.service;

import com.deukyun.realworld.article.application.port.in.DeleteArticleUseCase;
import com.deukyun.realworld.article.application.port.out.DeleteArticlePort;
import com.deukyun.realworld.common.component.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@UseCase
public class DeleteArticleService implements DeleteArticleUseCase {

    private final DeleteArticlePort deleteArticlePort;

    // TODO - favorite 을 지워주던가 체크를 해야함
    @Transactional
    @Override
    public void deleteArticle(long userId, String slug) {

        deleteArticlePort.deleteArticle(userId, slug);
    }
}
