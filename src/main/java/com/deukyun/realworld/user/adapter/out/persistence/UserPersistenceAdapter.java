package com.deukyun.realworld.user.adapter.out.persistence;

import com.deukyun.realworld.common.component.PersistenceAdapter;
import com.deukyun.realworld.common.exception.RealworldRuntimeException;
import com.deukyun.realworld.user.application.port.out.FindPasswordPort;
import com.deukyun.realworld.user.application.port.out.FindUserByIdPort;
import com.deukyun.realworld.user.application.port.out.InsertUserPort;
import com.deukyun.realworld.user.application.port.out.UpdateUserPort;
import com.deukyun.realworld.user.application.port.out.dto.command.InsertUserCommand;
import com.deukyun.realworld.user.application.port.out.dto.command.UpdateUserCommand;
import com.deukyun.realworld.user.application.port.out.dto.command.UpdateUserResult;
import com.deukyun.realworld.user.application.port.out.dto.query.FindPasswordResult;
import com.deukyun.realworld.user.application.port.out.dto.query.FindUserByIdResult;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
@PersistenceAdapter
class UserPersistenceAdapter implements
        InsertUserPort,
        FindPasswordPort,
        UpdateUserPort,
        FindUserByIdPort {

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

    @Override
    public FindUserByIdResult findUserById(long id) {
        return userRepository.findByIdProjection(id).orElseThrow(RealworldRuntimeException::new);
    }
}
