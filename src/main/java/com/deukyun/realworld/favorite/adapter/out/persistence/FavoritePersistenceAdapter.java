package com.deukyun.realworld.favorite.adapter.out.persistence;

import com.deukyun.realworld.article.domain.Article.ArticleId;
import com.deukyun.realworld.common.component.PersistenceAdapter;
import com.deukyun.realworld.favorite.application.port.out.CheckFavoritePort;
import com.deukyun.realworld.favorite.application.port.out.CountFavoritesPort;
import com.deukyun.realworld.favorite.application.port.out.DeleteFavoritePort;
import com.deukyun.realworld.favorite.application.port.out.InsertFavoritePort;
import com.deukyun.realworld.favorite.domain.FavoriteId;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

import static com.deukyun.realworld.user.domain.User.UserId;
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
    public Optional<FavoriteId> checkFavorite(UserId userId, ArticleId article) {
        return favoriteRepository.findByUserIdEqualsAndArticleIdEquals(userId.getValue(), article.getValue())
                .map(FavoriteJpaEntity::getId)
                .map(FavoriteId::new);
    }

    @Override
    public List<Boolean> checkFavorites(UserId userId, List<ArticleId> articleIds) {
        List<Long> favoriteArticleIds = favoriteRepository.findFavoriteArticleIdsByUserId(userId.getValue());

        return articleIds.stream()
                .map(ArticleId::getValue)
                .map(id -> favoriteArticleIds.stream().anyMatch(id::equals)).collect(toList());
    }

    @Override
    public void insertFavorite(UserId userId, ArticleId articleId) {
        favoriteRepository.save(new FavoriteJpaEntity(userId.getValue(), articleId.getValue()));
    }

    @Override
    public void deleteById(FavoriteId id) {
        FavoriteJpaEntity deleteTarget = favoriteRepository.findById(id.getValue()).orElseThrow(IllegalStateException::new);
        favoriteRepository.delete(deleteTarget);
    }

    @Override
    public long countFavorite(ArticleId articleId) {
        return favoriteRepository.countByArticleId(articleId.getValue());
    }
}
