package com.deukyun.realworld.favorite.application.port.in;

import com.deukyun.realworld.user.domain.User.UserId;

public interface FavoriteArticleUseCase {

    FavoriteArticleResult favorite(UserId userId, String slug);
}
