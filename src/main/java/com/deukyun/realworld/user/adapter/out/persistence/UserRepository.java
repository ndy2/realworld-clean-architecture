package com.deukyun.realworld.user.adapter.out.persistence;

import com.deukyun.realworld.user.application.port.out.FindPasswordResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

interface UserRepository extends JpaRepository<UserJpaEntity, Long> {

    @Query("select new com.deukyun.realworld.user.application.port.out.FindPasswordResponse(u.id, u.password) " +
            "from UserJpaEntity u " +
            "where u.email = :email")
    Optional<FindPasswordResponse> findPasswordByEmail(String email);
}
