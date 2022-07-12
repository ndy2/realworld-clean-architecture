package com.deukyun.realworld.follow.adapter.out.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Import(FollowPersistenceAdapter.class)
@DataJpaTest
class FollowPersistenceAdapterTest {


    @Autowired
    FollowPersistenceAdapter followPersistenceAdapter;

    @Test
    void 팔로우_체크_테스트() {
        //given
        followPersistenceAdapter.insertFollow(1, 2);

        //when
        boolean isFollow1 = followPersistenceAdapter.checkFollow(1, 2).isPresent();
        boolean isFollow2 = followPersistenceAdapter.checkFollow(1, 3).isPresent();

        //then
        assertThat(isFollow1).isTrue();
        assertThat(isFollow2).isFalse();
    }

    @Test
    void 팔로우_체크_목록_테스트() {
        //given
        followPersistenceAdapter.insertFollow(1, 2);
        followPersistenceAdapter.insertFollow(1, 3);
        followPersistenceAdapter.insertFollow(2, 1);
        followPersistenceAdapter.insertFollow(2, 4);

        //when
        List<Boolean> flags = followPersistenceAdapter.checkFollows(1, List.of(2L, 3L, 4L));

        //then
        assertThat(flags).hasSize(3);
        assertThat(flags).containsExactly(true, true, false);
    }
}