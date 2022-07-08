package com.deukyun.realworld.common.base;


import lombok.EqualsAndHashCode;
import lombok.Getter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@EqualsAndHashCode
@MappedSuperclass
public abstract class BaseIdEntity {

    @Id
    @GeneratedValue
    private Long id;

}
