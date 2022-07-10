package com.deukyun.realworld.favorite.application.service;

import com.deukyun.realworld.common.component.UseCase;
import com.deukyun.realworld.favorite.application.port.in.FavoriteArticleResult;
import com.deukyun.realworld.favorite.application.port.in.FavoriteArticleUseCase;
import com.deukyun.realworld.favorite.application.port.in.UnfavoriteArticleUseCase;
import com.deukyun.realworld.favorite.application.port.out.CheckFavoritePort;
import com.deukyun.realworld.favorite.application.port.out.DeleteFavoritePort;
import com.deukyun.realworld.follow.application.port.out.InsertFollowPort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
class FavoriteArticleService implements
        FavoriteArticleUseCase,
        UnfavoriteArticleUseCase {

    /*    private final FindProfileByUserIdPort findProfileByUserIdPort;
        private final FindProfileByUsernamePort findProfileByUsernamePort;*/
    private final CheckFavoritePort checkFavoritePort;
    private final InsertFollowPort insertFollowPort;
    private final DeleteFavoritePort deleteFavoritePort;

    @Override
    public FavoriteArticleResult favorite(long userId, String slug) {
        return null;
    }

    @Override
    public FavoriteArticleResult unfavorite(long userId, String slug) {
        return null;
    }
}
