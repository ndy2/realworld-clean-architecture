package com.deukyun.realworld.user.adapter.out.persistence;

import com.deukyun.realworld.common.component.PersistenceAdapter;
import com.deukyun.realworld.common.exception.RealworldRuntimeException;
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
    public Optional<FindPasswordResult> findPasswordByEmail(String email) {

        return userRepository.findPasswordByEmail(email);
    }

    @Override
    public UpdateUserResult updateUser(UpdateUserCommand updateUserCommand) {

        UserJpaEntity userJpaEntity =
                userRepository.findById(updateUserCommand.getId()).orElseThrow(RealworldRuntimeException::new);

        userJpaEntity.update(
                updateUserCommand.getEmail(),
                updateUserCommand.getPassword()
        );

        return new UpdateUserResult(
                userJpaEntity.getEmail()
        );
    }
}
