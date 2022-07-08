package com.deukyun.realworld.user.adapter.out.persistence;

import com.deukyun.realworld.common.component.PersistenceAdapter;
import com.deukyun.realworld.user.application.port.out.FindPasswordPort;
import com.deukyun.realworld.user.application.port.out.InsertUserCommand;
import com.deukyun.realworld.user.application.port.out.InsertUserPort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@PersistenceAdapter
class UserPersistenceAdapter implements
        InsertUserPort,
        FindPasswordPort {

    private final UserRepository userRepository;


    @Override
    public void insertUser(InsertUserCommand insertUserCommand) {

        userRepository.save(
                new UserJpaEntity(
                        insertUserCommand.getEmail(),
                        insertUserCommand.getPassword(),
                        insertUserCommand.getProfileId()
                )
        );
    }

    @Override
    public Optional<String> findPasswordByEmail(String email) {

        return userRepository.findPasswordByEmail(email);
    }
}
