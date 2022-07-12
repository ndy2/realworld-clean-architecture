package com.deukyun.realworld.article.application.port.in;

import com.deukyun.realworld.article.application.port.in.dto.query.ArticleResult;
import com.deukyun.realworld.article.application.port.in.dto.query.FeedArticlesQuery;
import com.deukyun.realworld.article.application.port.in.dto.query.GetArticleBySlugQuery;
import com.deukyun.realworld.article.application.port.in.dto.query.ListArticlesQuery;

import java.util.List;

public interface ArticleQueries {

    List<ArticleResult> listArticles(ListArticlesQuery listArticlesQuery);

    List<ArticleResult> feedArticles(FeedArticlesQuery listArticlesCommand);

    ArticleResult getArticleBySlug(GetArticleBySlugQuery getArticleBySlugQuery);
}
