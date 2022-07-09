package com.deukyun.realworld.follow.adapter.out.persistence;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

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
        boolean isFollow1 = followPersistenceAdapter.checkFollow(1, 2);
        boolean isFollow2 = followPersistenceAdapter.checkFollow(1, 3);

        //then
        assertThat(isFollow1).isTrue();
        assertThat(isFollow2).isFalse();
    }
}