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

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Query
class GetArticleService implements
        ArticleQueries {

    private final FindArticlesByFieldsPort findArticlesByFieldsPort;
    private final FindFeedArticlesPort findFeedArticlesPort;
    private final FindArticleBySlugPort findArticleBySlugPort;
    private final FindProfileIdByUserIdPort findProfileIdByUserIdPort;
    private final CheckFollowPort checkFollowPort;
    private final CheckFavoritePort checkFavoritePort;
    private final CountFavoritesPort countFavoritesPort;

    @Override
    public List<ArticleResult> listArticles(ListArticlesCommand command) {
        Long userId = command.getUserId();

        // 검색 조건에 따라 아티클 목록 조회
        return findArticlesByFieldsPort.findArticlesByFields(
                        new FindArticlesByFieldsCommand(
                                command.getTag(),
                                command.getAuthor(),
                                command.getFavorited(),
                                command.getLimit(),
                                command.getOffset()
                        )
                ).stream()
                .map(a -> toArticleResult(userId, a))
                .collect(toList());
    }

    @Override
    public List<ArticleResult> feedArticles(FeedArticlesCommand command) {
        Long userId = command.getUserId();

        findFeedArticlesPort.findFeedArticles(
                new FindFeedArticleCommand(
                        command.getLimit(),
                        command.getOffset(),
                        command.getUserId()
                )
        );

        return null;
    }

    @Override
    public ArticleResult getArticleBySlug(GetArticleBySlugCommand command) {
        String slug = command.getSlug();
        Long userId = command.getUserId();

        // 슬러그로 아티클 조회
        FindArticleResult article = findArticleBySlugPort.findArticleBySlug(slug);

        return toArticleResult(userId, article);
    }

    private ArticleResult toArticleResult(Long userId, FindArticleResult article) {
        long articleId = article.getId();
        FindAuthorResult author = article.getAuthor();

        // 사용자 flag 조회
        List<Boolean> flags = userRelatedFlags(userId, author, articleId);
        boolean isFollow = flags.get(0);
        boolean isFavorited = flags.get(1);

        // 페이보릿 카운트 확인
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

    /**
     * 사용자와 관련된 기사의 flag 값들 반환
     * list[0] -> 팔로우 여부
     * list[1] -> 페이보릿 여부
     */
    private List<Boolean> userRelatedFlags(Long userId, FindAuthorResult author, long articleId) {

        boolean isFollow = false;
        boolean isFavorited = false;

        if (userId != null) {
            long userProfileId = findProfileIdByUserIdPort.findProfileIdByUserId(userId);

            isFollow = checkFollowPort.checkFollow(userProfileId, author.getId()).isPresent();
            isFavorited = checkFavoritePort.checkFavorite(userId, articleId).isPresent();
        }
        return List.of(isFollow, isFavorited);
    }
}
