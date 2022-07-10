package com.deukyun.realworld.favorite.application.service;

import com.deukyun.realworld.article.application.port.out.FindArticleBySlugPort;
import com.deukyun.realworld.article.application.port.out.FindArticleResult;
import com.deukyun.realworld.article.application.port.out.FindAuthorResult;
import com.deukyun.realworld.common.component.UseCase;
import com.deukyun.realworld.favorite.application.port.in.FavoriteArticleResult;
import com.deukyun.realworld.favorite.application.port.in.FavoriteArticleUseCase;
import com.deukyun.realworld.favorite.application.port.in.FavoriteAuthorResult;
import com.deukyun.realworld.favorite.application.port.in.UnfavoriteArticleUseCase;
import com.deukyun.realworld.favorite.application.port.out.CheckFavoritePort;
import com.deukyun.realworld.favorite.application.port.out.DeleteFavoritePort;
import com.deukyun.realworld.favorite.application.port.out.InsertFavoritePort;
import com.deukyun.realworld.follow.application.port.out.CheckFollowPort;
import com.deukyun.realworld.profile.application.port.out.FindProfileIdByUserIdPort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;

@RequiredArgsConstructor
@UseCase
class FavoriteArticleService implements
        FavoriteArticleUseCase,
        UnfavoriteArticleUseCase {

    private final FindProfileIdByUserIdPort findProfileIdByUserIdPort;
    private final FindArticleBySlugPort findArticleBySlugPort;
    private final CheckFollowPort checkFollowPort;
    private final CheckFavoritePort checkFavoritePort;
    private final InsertFavoritePort insertFavoritePort;
    private final DeleteFavoritePort deleteFavoritePort;

    @Transactional
    @Override
    public FavoriteArticleResult favorite(long userId, String slug) {

        // 1. 슬러그로 아티클 조회
        FindArticleResult article = findArticleBySlugPort.findArticleBySlug(slug);
        long articleId = article.getId();

        // 2. 이미 페이보릿 인지 확인하고 페이보릿 추가
        checkFavoritable(userId, articleId);
        insertFavoritePort.insertFavorite(userId, articleId);

        // 3. 팔로우 여부를 확인 하기 위해 자신의 프로필 아이디 조회
        long userProfileId = findProfileIdByUserIdPort.findProfileIdByUserId(userId);

        // 4. 팔로우 여부 확인
        FindAuthorResult author = article.getAuthor();
        boolean isFollow = checkFollowPort.checkFollow(userProfileId, author.getId()).isPresent();

        // 5. 데이터 반환
        return new FavoriteArticleResult(
                article.getSlug(),
                article.getTitle(),
                article.getDescription(),
                article.getBody(),
                article.getTagList(),
                article.getCreatedAt(),
                article.getUpdatedAt(),
                true,
                1,
                new FavoriteAuthorResult(
                        author.getUsername(),
                        author.getBio(),
                        author.getImage(),
                        isFollow
                )
        );
    }

    private void checkFavoritable(long userId, long articleId) {
        boolean isAlreadyFavorite = checkFavoritePort.checkFavorite(userId, articleId).isPresent();

        checkArgument(!isAlreadyFavorite, "이미 페이보릿 입니다");
    }

    @Transactional
    @Override
    public FavoriteArticleResult unfavorite(long userId, String slug) {
        // 1. 슬러그로 아티클 조회
        FindArticleResult article = findArticleBySlugPort.findArticleBySlug(slug);
        long articleId = article.getId();

        // 2. 언페이보릿 할 수 있는지 확인하고 페이보릿 제거
        long favoriteId = checkUnfFavoritable(userId, articleId);
        deleteFavoritePort.deleteById(favoriteId);

        // 3. 팔로우 여부를 확인 하기 위해 자신의 프로필 아이디 조회
        long userProfileId = findProfileIdByUserIdPort.findProfileIdByUserId(userId);

        // 4. 팔로우 여부 확인
        FindAuthorResult author = article.getAuthor();
        boolean isFollow = checkFollowPort.checkFollow(userProfileId, author.getId()).isPresent();

        // 5. 데이터 반환
        return new FavoriteArticleResult(
                article.getSlug(),
                article.getTitle(),
                article.getDescription(),
                article.getBody(),
                article.getTagList(),
                article.getCreatedAt(),
                article.getUpdatedAt(),
                true,
                1,
                new FavoriteAuthorResult(
                        author.getUsername(),
                        author.getBio(),
                        author.getImage(),
                        isFollow
                )
        );
    }

    private long checkUnfFavoritable(long userId, long articleId) {
        Optional<Long> idIfPresent = checkFavoritePort.checkFavorite(userId, articleId);

        boolean isAlreadyFavorite = idIfPresent.isPresent();

        checkArgument(isAlreadyFavorite, "페이보릿 상태가 아닙니다");
        return idIfPresent.get();
    }
}
