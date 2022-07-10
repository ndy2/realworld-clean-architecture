package com.deukyun.realworld.favorite.application.port.out;

import java.util.Optional;

public interface CheckFavoritePort {

    /**
     * 아티클에 대해 페이포릿을 이미 추가 하였는지 확인
     * <p>
     * 존재한다면 id 리턴, 존재하지 않으면 null 리턴
     */
    Optional<Long> checkFavorite(long userId, long articleId);
}
