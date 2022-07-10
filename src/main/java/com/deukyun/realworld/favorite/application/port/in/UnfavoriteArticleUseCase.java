package com.deukyun.realworld.favorite.application.port.in;

public interface UnfavoriteArticleUseCase {

    FavoriteArticleResult unfavorite(long userId, String slug);
}
