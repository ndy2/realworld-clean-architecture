package com.deukyun.realworld.user.adapter.out.persistence;

import com.deukyun.realworld.user.application.port.out.dto.query.FindPasswordResult;
import com.deukyun.realworld.user.application.port.out.dto.query.FindUserByIdResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

interface UserRepository extends JpaRepository<UserJpaEntity, Long> {

    @Query("select new com.deukyun.realworld.user.application.port.out.dto.query.FindPasswordResult(u.id, u.password) " +
            "from UserJpaEntity u " +
            "where u.email = :email")
    Optional<FindPasswordResult> findPasswordByEmail(String email);

    @Query("select new com.deukyun.realworld.user.application.port.out.dto.query.FindUserByIdResult(u.email) " +
            "from UserJpaEntity u " +
            "where u.id = :id")
    Optional<FindUserByIdResult> findByIdProjection(long id);
}
