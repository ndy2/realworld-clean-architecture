package com.deukyun.realworld.article.application.service;

import com.deukyun.realworld.article.application.port.in.ArticleQueries;
import com.deukyun.realworld.article.application.port.in.ArticleResult;
import com.deukyun.realworld.article.application.port.in.FeedArticlesCommand;
import com.deukyun.realworld.article.application.port.in.ListArticlesCommand;
import com.deukyun.realworld.article.application.port.out.FindArticleBySlugPort;
import com.deukyun.realworld.article.application.port.out.FindArticleResult;
import com.deukyun.realworld.article.application.port.out.FindArticlesByFieldsCommand;
import com.deukyun.realworld.article.application.port.out.FindArticlesByFieldsPort;
import com.deukyun.realworld.common.component.Query;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Query
class GetArticleService implements
        ArticleQueries {

    private final FindArticlesByFieldsPort findArticles;
    private final FindArticleBySlugPort findArticleBySlugPort;

    @Override
    public List<ArticleResult> listArticles(ListArticlesCommand listArticlesCommand) {
        List<FindArticleResult> articleResults = findArticles.findArticlesByFields(
                new FindArticlesByFieldsCommand(
                        listArticlesCommand.getTag(),
                        listArticlesCommand.getAuthor(),
                        listArticlesCommand.getFavorited(),
                        listArticlesCommand.getLimit(),
                        listArticlesCommand.getOffset()
                )
        );

        return null;
    }

    @Override
    public List<ArticleResult> feedArticles(FeedArticlesCommand listArticlesCommand) {
        return null;
    }

    @Override
    public ArticleResult getArticleBySlug(String slug) {

        FindArticleResult articleResult = findArticleBySlugPort.findArticleBySlug(slug);

        return null;
    }
}
