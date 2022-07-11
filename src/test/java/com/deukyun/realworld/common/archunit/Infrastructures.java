package com.deukyun.realworld.common.archunit;

import com.tngtech.archunit.core.domain.JavaClasses;

import java.util.List;

public class Infrastructures extends ArchitectureElement {

    private final HexagonalArchitecture parentContext;

    public Infrastructures(String basePackage, HexagonalArchitecture parentContext) {
        super(basePackage);
        this.parentContext = parentContext;
    }

    public void doesNotDependOn(String packageName, JavaClasses classes) {
        denyDependency(this.basePackage, packageName, classes);
    }

    public void doesNotDependOnAny(List<String> packageNames, JavaClasses classes) {
        denyAnyDependency(List.of(this.basePackage), packageNames, classes);
    }
}
