package com.deukyun.realworld.favorite.adapter.out.persistence;

import com.deukyun.realworld.common.component.PersistenceAdapter;
import com.deukyun.realworld.favorite.application.port.out.CheckFavoritePort;
import com.deukyun.realworld.favorite.application.port.out.CountFavoritesPort;
import com.deukyun.realworld.favorite.application.port.out.DeleteFavoritePort;
import com.deukyun.realworld.favorite.application.port.out.InsertFavoritePort;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@PersistenceAdapter
class FavoritePersistenceAdapter implements
        CheckFavoritePort,
        InsertFavoritePort,
        DeleteFavoritePort,
        CountFavoritesPort {

    private final FavoriteRepository favoriteRepository;

    @Override
    public Optional<Long> checkFavorite(long userId, long article) {
        return favoriteRepository.findByUserIdEqualsAndArticleIdEquals(userId, article)
                .map(FavoriteJpaEntity::getId);
    }

    @Override
    public List<Boolean> checkFavorites(Long userId, List<Long> articleIds) {
        List<Long> favoriteArticleIds = favoriteRepository.findFavoriteArticleIdsByUserId(userId);

        return articleIds.stream()
                .map(id -> favoriteArticleIds.stream().anyMatch(id::equals)).collect(toList());
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

    @Override
    public long countFavorite(long articleId) {
        return favoriteRepository.countByArticleId(articleId);
    }
}
