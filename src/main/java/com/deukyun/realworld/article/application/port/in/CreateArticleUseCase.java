package com.deukyun.realworld.article.application.port.in;

public interface CreateArticleUseCase {

    CreateArticleResult createArticle(CreateArticleCommand createArticleCommand);
}
