package com.deukyun.realworld.favorite.application.port.in;

import com.deukyun.realworld.favorite.application.port.in.dto.command.FavoriteArticleResult;

public interface FavoriteArticleUseCases {

    FavoriteArticleResult favorite(long userId, String slug);

    FavoriteArticleResult unfavorite(long userId, String slug);
}
