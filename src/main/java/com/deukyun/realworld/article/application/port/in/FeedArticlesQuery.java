package com.deukyun.realworld.article.application.port.in;

import java.util.List;

public interface FeedArticlesQuery {

    List<ArticleResult> feedArticles(FeedArticlesCommand feedArticlesCommand);
}
