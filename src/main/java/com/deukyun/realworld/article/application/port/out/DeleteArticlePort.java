package com.deukyun.realworld.article.application.port.out;

public interface DeleteArticlePort {

    void deleteArticle(long userId, String slug);
}
