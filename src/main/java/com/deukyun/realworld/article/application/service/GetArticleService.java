package com.deukyun.realworld.article.application.service;

import com.deukyun.realworld.article.application.port.in.*;
import com.deukyun.realworld.article.application.port.out.*;
import com.deukyun.realworld.common.component.Query;
import com.deukyun.realworld.favorite.application.port.out.CheckFavoritePort;
import com.deukyun.realworld.favorite.application.port.out.CountFavoritesPort;
import com.deukyun.realworld.follow.application.port.out.CheckFollowPort;
import com.deukyun.realworld.profile.application.port.out.FindProfileIdByUserIdPort;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Query
class GetArticleService implements
        ArticleQueries {

    private final FindArticlesByFieldsPort findArticles;
    private final FindArticleBySlugPort findArticleBySlugPort;
    private final FindProfileIdByUserIdPort findProfileIdByUserIdPort;
    private final CheckFollowPort checkFollowPort;
    private final CheckFavoritePort checkFavoritePort;
    private final CountFavoritesPort countFavoritesPort;

    @Override
    public List<ArticleResult> listArticles(ListArticlesCommand listArticlesCommand) {
        List<FindArticleResult> articleResults = findArticles.findArticlesByFields(
                new FindArticlesByFieldsCommand(
                        listArticlesCommand.getTag(),
                        listArticlesCommand.getAuthor(),
                        listArticlesCommand.getFavorited(),
                        listArticlesCommand.getLimit(),
                        listArticlesCommand.getOffset()
                )
        );

        return null;
    }

    @Override
    public List<ArticleResult> feedArticles(FeedArticlesCommand listArticlesCommand) {
        return null;
    }

    @Override
    public ArticleResult getArticleBySlug(GetArticleBySlugCommand command) {
        String slug = command.getSlug();
        Long userId = command.getUserId();

        // 1. 슬러그로 아티클 조회
        FindArticleResult article = findArticleBySlugPort.findArticleBySlug(slug);
        long articleId = article.getId();

        FindAuthorResult author = article.getAuthor();

        boolean isFollow = false;
        boolean isFavorited = false;

        if (userId != null) {
            // 2. 팔로우 여부를 확인 하기 위해 자신의 프로필 아이디 조회
            long userProfileId = findProfileIdByUserIdPort.findProfileIdByUserId(userId);

            // 3. 팔로우, 페이보릿 여부 확인
            isFollow = checkFollowPort.checkFollow(userProfileId, author.getId()).isPresent();
            isFavorited = checkFavoritePort.checkFavorite(userId, articleId).isPresent();
        }

        // 4. 페이보릿 카운트 확인
        long favoritesCount = countFavoritesPort.countFavorite(articleId);

        return new ArticleResult(
                article.getSlug(),
                article.getTitle(),
                article.getDescription(),
                article.getBody(),
                article.getTagList(),
                article.getCreatedAt(),
                article.getUpdatedAt(),
                isFavorited,
                favoritesCount,
                new AuthorResult(
                        author.getUsername(),
                        author.getBio(),
                        author.getImage(),
                        isFollow
                )
        );
    }
}
