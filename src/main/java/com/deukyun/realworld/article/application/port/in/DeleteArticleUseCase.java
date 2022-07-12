package com.deukyun.realworld.article.application.port.in;

public interface DeleteArticleUseCase {

    void deleteArticle(long userId, String slug);
}
