package com.deukyun.realworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan(basePackages = "com.deukyun.realworld.infrastructure")
@SpringBootApplication
public class RealworldApplication {

  public static void main(String[] args) {
    SpringApplication.run(RealworldApplication.class, args);
  }

}
