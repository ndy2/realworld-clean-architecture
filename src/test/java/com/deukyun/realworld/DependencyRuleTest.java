package com.deukyun.realworld;

import com.deukyun.realworld.archunit.HexagonalArchitecture;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * archUnit 을 활용한 의존성 규칙 테스트
 * <li> 만들면서 배우는 클랜 아키텍처 - p123, <a href = "https://github.com/wikibook/clean-architecture">깃헙</a>  </li>
 * <li> Naver D2 - <a href="https://d2.naver.com/helloworld/9222129">ArchUnit - UnitTest로 아키텍처 검사</a> </li>
 */
class DependencyRuleTest {

    JavaClasses importPackages = new ClassFileImporter().importPackages("com.deukyun.realworld..");

    @ParameterizedTest
    @ValueSource(strings = {"user", "profile", "follow"})
    void Hexagonal_Architecture_의존성_규칙을_만족한다(String boundedContext) {

        HexagonalArchitecture.boundedContext("com.deukyun.realworld", boundedContext)
                .withDomainLayer("domain")

                .withAdaptersLayer("adapter")
                .incoming("in.web")
                .outgoing("out.persistence")
                .and()

                .withApplicationLayer("application")
                .services("service")
                .incomingPorts("port.in")
                .outgoingPorts("port.out")
                .and()

                .withConfiguration("configuration")
                .withInfrastructure("infrastructure")
                .check(importPackages);
    }
}
