package com.deukyun.realworld.article.application.port.out;

import com.deukyun.realworld.article.application.port.out.dto.query.FindArticleResult;
import com.deukyun.realworld.article.application.port.out.dto.query.FindFeedArticleQuery;

import java.util.List;

public interface FindFeedArticlesPort {

    List<FindArticleResult> findFeedArticles(FindFeedArticleQuery command);
}
