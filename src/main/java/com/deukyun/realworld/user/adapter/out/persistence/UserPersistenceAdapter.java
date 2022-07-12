package com.deukyun.realworld.user.adapter.out.persistence;

import com.deukyun.realworld.common.component.PersistenceAdapter;
import com.deukyun.realworld.common.exception.RealworldRuntimeException;
import com.deukyun.realworld.user.application.port.out.*;
import com.deukyun.realworld.user.domain.Email;
import com.deukyun.realworld.user.domain.Password;
import com.deukyun.realworld.user.domain.User.UserId;
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
    public UserId insertUser(InsertUserCommand insertUserCommand) {

        return new UserId(userRepository.save(
                new UserJpaEntity(
                        insertUserCommand.getEmail().getValue(),
                        insertUserCommand.getPassword().getValue()
                )
        ).getId());
    }

    @Override
    public Optional<FindPasswordResult> findPasswordByEmail(Email email) {

        return userRepository.findPasswordByEmail(email.getValue());
    }

    @Override
    public UpdateUserResult updateUser(UpdateUserCommand updateUserCommand) {
        UserId userId = updateUserCommand.getUserId();
        Email email = updateUserCommand.getEmail();
        Password password = updateUserCommand.getPassword();

        UserJpaEntity userJpaEntity
                = userRepository.findById(userId.getValue()).orElseThrow(RealworldRuntimeException::new);

        userJpaEntity.update(
                email == null ? null : email.getValue(),
                password == null ? null : password.getValue()
        );

        return new UpdateUserResult(email);
    }

    @Override
    public FindUserByIdResult findUserById(UserId userId) {
        return userRepository.findByIdProjection(userId.getValue()).orElseThrow(RealworldRuntimeException::new);
    }
}
