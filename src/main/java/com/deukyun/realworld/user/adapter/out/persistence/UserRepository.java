package com.deukyun.realworld.user.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

interface UserRepository extends JpaRepository<UserJpaEntity, Long> {

    @Query("select u.password from UserJpaEntity u where u.email = :email")
    Optional<String> findPasswordByEmail(String email);
}
