package com.deukyun.realworld.user.adapter.out.persistence;

import com.deukyun.realworld.user.application.port.out.FindPasswordResponse;
import com.deukyun.realworld.user.application.port.out.InsertUserCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Import(UserPersistenceAdapter.class)
@DataJpaTest
class UserPersistenceAdapterTest {

    @Autowired
    UserPersistenceAdapter userPersistenceAdapter;

    @Test
    void 사용자_삽입_이메일로_조회() {
        //given
        InsertUserCommand insertUserCommand
                = new InsertUserCommand(
                "jake@jake.jake",
                "[encoded]jakejake",
                1L
        );

        //when
        userPersistenceAdapter.insertUser(insertUserCommand);
        Optional<FindPasswordResponse> findPasswordResponse = userPersistenceAdapter.findPasswordByEmail("jake@jake.jake");

        //then
        assertThat(findPasswordResponse).isPresent();
        assertThat(findPasswordResponse.get().getId()).isNotNull();
        assertThat(findPasswordResponse.get().getPassword()).isEqualTo("[encoded]jakejake");
    }
}