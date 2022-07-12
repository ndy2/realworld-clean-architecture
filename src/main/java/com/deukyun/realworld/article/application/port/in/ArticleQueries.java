package com.deukyun.realworld.article.application.port.in;

import java.util.List;

public interface ArticleQueries {

    List<ArticleResult> listArticles(ListArticlesQuery listArticlesQuery);

    List<ArticleResult> feedArticles(FeedArticlesQuery listArticlesCommand);

    ArticleResult getArticleBySlug(GetArticleBySlugQuery getArticleBySlugQuery);
}
