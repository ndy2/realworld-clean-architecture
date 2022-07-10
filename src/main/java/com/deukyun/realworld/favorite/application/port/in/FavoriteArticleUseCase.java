package com.deukyun.realworld.favorite.application.port.in;

public interface FavoriteArticleUseCase {

    FavoriteArticleResult favorite(long userId, String slug);
}
