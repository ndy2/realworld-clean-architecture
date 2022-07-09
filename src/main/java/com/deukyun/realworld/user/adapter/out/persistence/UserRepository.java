package com.deukyun.realworld.user.adapter.out.persistence;

import com.deukyun.realworld.user.application.port.out.FindPasswordResult;
import com.deukyun.realworld.user.application.port.out.FindUserByIdResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

interface UserRepository extends JpaRepository<UserJpaEntity, Long> {

    @Query("select new com.deukyun.realworld.user.application.port.out.FindPasswordResult(u.id, u.password) " +
            "from UserJpaEntity u " +
            "where u.email = :email")
    Optional<FindPasswordResult> findPasswordByEmail(String email);

    @Query("select new com.deukyun.realworld.user.application.port.out.FindUserByIdResult(u.email) " +
            "from UserJpaEntity u " +
            "where u.id = :userId")
    Optional<FindUserByIdResult> findByIdProjection(long id);
}
