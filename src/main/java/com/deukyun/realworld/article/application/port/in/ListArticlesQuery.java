package com.deukyun.realworld.article.application.port.in;

import java.util.List;

public interface ListArticlesQuery {

    List<ArticleResult> listArticles(ListArticlesCommand listArticlesCommand);
}
