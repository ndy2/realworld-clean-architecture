package com.deukyun.realworld.favorite.application.port.out;

import com.deukyun.realworld.favorite.domain.FavoriteId;

public interface DeleteFavoritePort {

    void deleteById(FavoriteId favoriteId);
}
