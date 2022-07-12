package com.deukyun.realworld.article.application.service;

import com.deukyun.realworld.article.application.port.in.*;
import com.deukyun.realworld.article.application.port.out.*;
import com.deukyun.realworld.common.component.Query;
import com.deukyun.realworld.favorite.application.port.out.CheckFavoritePort;
import com.deukyun.realworld.favorite.application.port.out.CountFavoritesPort;
import com.deukyun.realworld.follow.application.port.out.CheckFollowPort;
import com.deukyun.realworld.profile.application.port.out.FindProfileIdByUserIdPort;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
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
        List<FindArticleResult> articles = findArticlesByFieldsPort.findArticlesByFields(
                new FindArticlesByFieldsCommand(
                        command.getTag(),
                        command.getAuthor(),
                        command.getFavorited(),
                        command.getLimit(),
                        command.getOffset()
                )
        );

        return toArticleResults(articles, userId);
    }

    @Override
    public List<ArticleResult> feedArticles(FeedArticlesCommand command) {

        List<FindArticleResult> articles = findFeedArticlesPort.findFeedArticles(
                new FindFeedArticleCommand(
                        command.getLimit(),
                        command.getOffset(),
                        command.getUserId()
                )
        );

        return toArticleResults(articles, command.getUserId());
    }

    @Override
    public ArticleResult getArticleBySlug(GetArticleBySlugCommand command) {
        String slug = command.getSlug();
        Long userId = command.getUserId();

        // 슬러그로 아티클 조회
        FindArticleResult article = findArticleBySlugPort.findArticleBySlug(slug);
        if (userId == null) {
            return toArticleResult(article, false, false);
        }

        long articleId = article.getId();
        FindAuthorResult author = article.getAuthor();

        long userProfileId = findProfileIdByUserIdPort.findProfileIdByUserId(userId);

        boolean isFollow = checkFollowPort.checkFollow(userProfileId, author.getId()).isPresent();
        boolean isFavorited = checkFavoritePort.checkFavorite(userId, articleId).isPresent();

        return toArticleResult(article, isFollow, isFavorited);
    }

    /**
     * 디비에서 조회에온 아티클 정보에
     * 사용자 별로 팔로우여부, 페이보릿 여부 등을 말아서 반환한다.
     *
     * @param articles 디비 조회 아티클 목록
     * @param userId   사용자 아이디
     * @return 서비스 반환 아티클 목록
     */
    private List<ArticleResult> toArticleResults(List<FindArticleResult> articles, Long userId) {
        // 아이디가 없으면 (미인증 사용자)
        // 팔로우 여부와 페이보릿 여부를 모두 false 로 말아서 반환
        if (userId == null) {
            return articles.stream().map(article -> toArticleResult(article, false, false)).collect(toList());
        }

        long userProfileId = findProfileIdByUserIdPort.findProfileIdByUserId(userId);

        // db 조회 아티클의 아이디, 작가 프로필 아이디 목록
        List<Long> articleIds
                = articles.stream()
                .map(FindArticleResult::getId)
                .collect(toList());
        List<Long> authorProfileIds
                = articles.stream()
                .map(FindArticleResult::getAuthor)
                .map(FindAuthorResult::getId)
                .collect(toList());

        // 각 아티클 별 팔로우, 페이보릿 여부를 배열로 받음
        List<Boolean> followFlags = checkFollowPort.checkFollows(userProfileId, authorProfileIds);
        List<Boolean> favoriteFlags = checkFavoritePort.checkFavorites(userId, articleIds);

        // 결과 말기
        int articleCount = articles.size();
        List<ArticleResult> result = new ArrayList<>();
        for (int i = 0; i < articleCount; i++) {
            FindArticleResult article = articles.get(i);
            boolean follow = followFlags.get(i);
            boolean favorited = favoriteFlags.get(i);

            result.add(toArticleResult(article, follow, favorited));
        }
        return result;
    }

    /**
     * 단건 db 아티클 서비스 아티클로 변환
     */
    private ArticleResult toArticleResult(FindArticleResult article, boolean isFollow, boolean isFavorited) {
        long articleId = article.getId();
        FindAuthorResult author = article.getAuthor();

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
