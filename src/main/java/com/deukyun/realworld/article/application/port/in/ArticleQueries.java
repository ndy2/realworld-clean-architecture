package com.deukyun.realworld.article.application.port.in;

import java.util.List;

public interface ArticleQueries {

    List<ArticleResult> listArticles(ListArticlesCommand listArticlesCommand);

    List<ArticleResult> feedArticles(FeedArticlesCommand listArticlesCommand);

    ArticleResult getArticleBySlug(GetArticleBySlugCommand getArticleBySlugCommand);
}
