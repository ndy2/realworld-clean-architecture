package com.deukyun.realworld.follow.adapter.out.persistence;

import com.deukyun.realworld.profile.domain.Profile.ProfileId;
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
        followPersistenceAdapter.insertFollow(new ProfileId(1L), new ProfileId(2L));

        //when
        boolean isFollow1 = followPersistenceAdapter.checkFollow(new ProfileId(1L), new ProfileId(2L)).isPresent();
        boolean isFollow2 = followPersistenceAdapter.checkFollow(new ProfileId(1L), new ProfileId(3L)).isPresent();

        //then
        assertThat(isFollow1).isTrue();
        assertThat(isFollow2).isFalse();
    }

    @Test
    void 팔로우_체크_목록_테스트() {
        //given
        followPersistenceAdapter.insertFollow(new ProfileId(1L), new ProfileId(2L));
        followPersistenceAdapter.insertFollow(new ProfileId(1L), new ProfileId(3L));
        followPersistenceAdapter.insertFollow(new ProfileId(2L), new ProfileId(1L));
        followPersistenceAdapter.insertFollow(new ProfileId(2L), new ProfileId(4L));

        //when
        List<Boolean> flags
                = followPersistenceAdapter.checkFollows(
                new ProfileId(1L),
                List.of(new ProfileId(2L), new ProfileId(3L), new ProfileId(4L))
        );

        //then
        assertThat(flags).hasSize(3);
        assertThat(flags).containsExactly(true, true, false);
    }
}