package com.deukyun.realworld.favorite.application.port.out;

import com.deukyun.realworld.user.domain.User.UserId;

public interface InsertFavoritePort {

    void insertFavorite(UserId userId, long articleId);
}
