package com.deukyun.realworld.article.application.service;


import com.deukyun.realworld.article.application.port.in.UpdateArticleUseCase;
import com.deukyun.realworld.article.application.port.in.dto.command.AuthorResult;
import com.deukyun.realworld.article.application.port.in.dto.command.UpdateArticleCommand;
import com.deukyun.realworld.article.application.port.in.dto.command.UpdateArticleResult;
import com.deukyun.realworld.article.application.port.out.UpdateArticlePort;
import com.deukyun.realworld.article.application.port.out.dto.command.CommandAuthorResult;
import com.deukyun.realworld.article.application.port.out.dto.command.UpdateArticleStateCommand;
import com.deukyun.realworld.article.application.port.out.dto.command.UpdateArticleStateResult;
import com.deukyun.realworld.common.component.UseCase;
import com.deukyun.realworld.favorite.application.port.out.CheckFavoritePort;
import com.deukyun.realworld.favorite.application.port.out.CountFavoritesPort;
import com.deukyun.realworld.follow.application.port.out.CheckFollowPort;
import com.deukyun.realworld.profile.application.port.out.FindProfileIdByUserIdPort;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@UseCase
public class UpdateArticleService implements UpdateArticleUseCase {

    private final UpdateArticlePort updateArticlePort;
    private final CheckFavoritePort checkFavoritePort;
    private final CountFavoritesPort countFavoritesPort;
    private final CheckFollowPort checkFollowPort;
    private final FindProfileIdByUserIdPort findProfileIdByUserIdPort;

    @Transactional
    @Override
    public UpdateArticleResult updateArticle(UpdateArticleCommand command) {
        long userId = command.getUserId();
        long profileId = findProfileIdByUserIdPort.findProfileIdByUserId(userId);

        String origSlug = command.getSlug();
        String updateSlug = createSlug(command);
        UpdateArticleStateResult article = updateArticlePort.updateArticle(
                new UpdateArticleStateCommand(
                        userId,
                        command.getTitle(),
                        origSlug,
                        updateSlug,
                        command.getDescription(),
                        command.getBody()
                )
        );
        CommandAuthorResult author = article.getAuthor();

        long articleId = article.getId();
        boolean isFavorited = checkFavoritePort.checkFavorite(userId, articleId).isPresent();
        long favoritesCount = countFavoritesPort.countFavorite(articleId);
        boolean isFollowing = checkFollowPort.checkFollow(profileId, author.getId()).isPresent();

        return new UpdateArticleResult(
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
                        isFollowing
                )
        );
    }

    /**
     * 타이틀을 이요해 Slug 를 만듬
     */
    private String createSlug(UpdateArticleCommand updateArticleCommand) {

        String title = updateArticleCommand.getTitle();

        return String.join("-", title.toLowerCase().split(" "));
    }
}
