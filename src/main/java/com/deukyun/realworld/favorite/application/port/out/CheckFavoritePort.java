package com.deukyun.realworld.favorite.application.port.out;

import com.deukyun.realworld.article.domain.Article.ArticleId;
import com.deukyun.realworld.favorite.domain.FavoriteId;
import com.deukyun.realworld.user.domain.User.UserId;

import java.util.List;
import java.util.Optional;

public interface CheckFavoritePort {

    /**
     * 아티클에 대해 페이포릿을 이미 추가 하였는지 확인
     * <p>
     * 존재한다면 id 리턴, 존재하지 않으면 null 리턴
     */
    Optional<FavoriteId> checkFavorite(UserId userId, ArticleId articleId);

    /**
     * 유저의 아티클 아이디 목록에 대한
     * 아티클의 페이보릿 여부를 반환
     */
    List<Boolean> checkFavorites(UserId userId, List<ArticleId> articleIds);
}
