package com.deukyun.realworld.favorite.application.port.in;

import com.deukyun.realworld.user.domain.User.UserId;

public interface UnfavoriteArticleUseCase {

    FavoriteArticleResult unfavorite(UserId userId, String slug);
}
