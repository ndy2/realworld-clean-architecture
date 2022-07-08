package com.deukyun.realworld.profile.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

interface ProfileRepository extends JpaRepository<ProfileJpaEntity, Long> {
}
