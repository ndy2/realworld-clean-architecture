package com.deukyun.realworld.article.application.port.out;

import java.util.List;

public interface FindFeedArticlesPort {

    List<FindArticleResult> findFeedArticles(FindFeedArticleQuery command);
}
