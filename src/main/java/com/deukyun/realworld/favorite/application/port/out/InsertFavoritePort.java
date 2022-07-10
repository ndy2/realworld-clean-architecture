package com.deukyun.realworld.favorite.application.port.out;

public interface InsertFavoritePort {

    void insertFavorite(long userId, long articleId);
}
