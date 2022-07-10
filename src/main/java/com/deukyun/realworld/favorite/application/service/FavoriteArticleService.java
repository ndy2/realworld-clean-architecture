package com.deukyun.realworld.favorite.application.service;

import com.deukyun.realworld.common.component.UseCase;
import com.deukyun.realworld.favorite.application.port.in.FavoriteArticleResult;
import com.deukyun.realworld.favorite.application.port.in.FavoriteArticleUseCase;
import com.deukyun.realworld.favorite.application.port.in.UnfavoriteArticleUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
class FavoriteArticleService implements
        FavoriteArticleUseCase,
        UnfavoriteArticleUseCase {


    @Override
    public FavoriteArticleResult favorite(long userId, String slug) {
        return null;
    }

    @Override
    public FavoriteArticleResult unfavorite(long userId, String slug) {
        return null;
    }
}
