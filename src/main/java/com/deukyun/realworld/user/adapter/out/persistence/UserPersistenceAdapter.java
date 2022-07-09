package com.deukyun.realworld.user.adapter.out.persistence;

import com.deukyun.realworld.common.component.PersistenceAdapter;
import com.deukyun.realworld.user.application.port.out.*;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@PersistenceAdapter
class UserPersistenceAdapter implements
        InsertUserPort,
        FindPasswordPort,
        UpdateUserPort {

    private final UserRepository userRepository;


    @Override
    public long insertUser(InsertUserCommand insertUserCommand) {

        return userRepository.save(
                new UserJpaEntity(
                        insertUserCommand.getEmail(),
                        insertUserCommand.getPassword()
                )
        ).getId();
    }

    @Override
    public Optional<FindPasswordResponse> findPasswordByEmail(String email) {

        return userRepository.findPasswordByEmail(email);
    }

    @Override
    public void updateUser(UpdateUserCommand updateUserCommand) {

        userRepository.findById(updateUserCommand.getId());
    }
}
