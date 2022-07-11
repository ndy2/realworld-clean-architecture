package com.deukyun.realworld.common;


import com.p6spy.engine.spy.P6SpyOptions;
import org.springframework.boot.test.context.TestConfiguration;

import javax.annotation.PostConstruct;

@TestConfiguration
public class P6spyLogMessageFormatConfiguration {

    @PostConstruct
    public void setLogMessageFormat() {
        System.out.println("log");
        P6SpyOptions.getActiveInstance().setLogMessageFormat(CustomP6spySqlFormat.class.getName());
    }

}

