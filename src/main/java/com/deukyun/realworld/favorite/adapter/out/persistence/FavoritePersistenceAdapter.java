package com.deukyun.realworld.favorite.adapter.out.persistence;

import com.deukyun.realworld.common.component.PersistenceAdapter;
import com.deukyun.realworld.favorite.application.port.out.DeleteFavoritePort;
import com.deukyun.realworld.favorite.application.port.out.InsertFavoritePort;
import com.deukyun.realworld.follow.application.port.out.CheckFollowPort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@PersistenceAdapter
class FavoritePersistenceAdapter implements
        CheckFollowPort,
        InsertFavoritePort,
        DeleteFavoritePort {

    private final FavoriteRepository favoriteRepository;

    @Override
    public Optional<Long> checkFollow(long userId, long article) {
        return favoriteRepository.findByUserIdEqualsAndArticleIdEquals(userId, article)
                .map(FavoriteJpaEntity::getId);
    }

    @Override
    public void insertFavorite(long userId, long articleId) {
        favoriteRepository.save(new FavoriteJpaEntity(userId, articleId));
    }

    @Override
    public void deleteById(long id) {
        FavoriteJpaEntity deleteTarget = favoriteRepository.findById(id).orElseThrow(IllegalStateException::new);
        favoriteRepository.delete(deleteTarget);
    }
}
