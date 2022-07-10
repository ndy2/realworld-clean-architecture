package com.deukyun.realworld.configuration.jwt;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@RequiredArgsConstructor
@ConstructorBinding
@ConfigurationProperties(prefix = "infrastructure.security.jwt")
public class JwtProperties {

    private final String secretKey;

    private final long expiration;
}
