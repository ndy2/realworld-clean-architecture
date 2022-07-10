package com.deukyun.realworld.article.application.port.out;

public interface InsertArticlePort {

    InsertArticleResult insertArticle(InsertArticleCommand insertArticleCommand);
}
