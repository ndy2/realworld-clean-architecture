package com.deukyun.realworld.favorite.application.port.out;

import com.deukyun.realworld.article.domain.Article.ArticleId;

public interface CountFavoritesPort {

    long countFavorite(ArticleId articleId);
}
