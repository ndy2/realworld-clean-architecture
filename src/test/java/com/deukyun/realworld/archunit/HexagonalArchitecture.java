package com.deukyun.realworld.archunit;

import com.tngtech.archunit.core.domain.JavaClasses;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HexagonalArchitecture extends ArchitectureElement {

    private Adapters adapters;
    private ApplicationLayer applicationLayer;
    private String configurationPackage;
    private Infrastructures infrastructures;
    private List<String> domainPackages = new ArrayList<>();
    private String base;

    public static HexagonalArchitecture boundedContext(String base, String boundedContext) {
        return new HexagonalArchitecture(base, boundedContext);
    }

    public HexagonalArchitecture(String base, String basePackage) {
        this(base + "." + basePackage);
        this.base = base;
    }

    public HexagonalArchitecture(String basePackage) {
        super(basePackage);
    }

    public Adapters withAdaptersLayer(String adaptersPackage) {
        this.adapters = new Adapters(this, fullQualifiedPackage(adaptersPackage));
        return this.adapters;
    }

    public HexagonalArchitecture withDomainLayer(String domainPackage) {
        this.domainPackages.add(fullQualifiedPackage(domainPackage));
        return this;
    }

    public ApplicationLayer withApplicationLayer(String applicationPackage) {
        this.applicationLayer = new ApplicationLayer(fullQualifiedPackage(applicationPackage), this);
        return this.applicationLayer;
    }

    public HexagonalArchitecture withConfiguration(String packageName) {
        this.configurationPackage = base + "." + packageName;
        return this;
    }

    private void domainDoesNotDependOnOtherPackages(JavaClasses classes) {
        denyAnyDependency(
                this.domainPackages, Collections.singletonList(adapters.basePackage), classes);
        denyAnyDependency(
                this.domainPackages, Collections.singletonList(applicationLayer.basePackage), classes);
    }

    public HexagonalArchitecture withInfrastructure(String packageName) {
        this.infrastructures = new Infrastructures(base + "." + packageName, this);
        return this;
    }

    public void check(JavaClasses classes) {
// 빈 패키지를 허용해야 하기 때문에 주석 처리
//    this.adapters.doesNotContainEmptyPackages();
        this.adapters.dontDependOnEachOther(classes);
        this.adapters.doesNotDependOn(this.configurationPackage, classes);
        // 빈 패키지를 허용해야 하기 때문에 주석 처리
//    this.applicationLayer.doesNotContainEmptyPackages();
        this.applicationLayer.doesNotDependOn(this.adapters.getBasePackage(), classes);
        this.applicationLayer.doesNotDependOn(this.configurationPackage, classes);
        this.applicationLayer.incomingAndOutgoingPortsDoNotDependOnEachOther(classes);

        this.infrastructures.doesNotDependOn(this.adapters.getBasePackage(), classes);
        this.infrastructures.doesNotDependOn(this.applicationLayer.getBasePackage(), classes);
        this.infrastructures.doesNotDependOnAny(this.domainPackages, classes);

        this.domainDoesNotDependOnOtherPackages(classes);
    }
}
