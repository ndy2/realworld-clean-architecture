package com.deukyun.realworld.favorite.application.port.out;

import com.deukyun.realworld.article.domain.Article.ArticleId;
import com.deukyun.realworld.user.domain.User.UserId;

public interface InsertFavoritePort {

    void insertFavorite(UserId userId, ArticleId articleId);
}
