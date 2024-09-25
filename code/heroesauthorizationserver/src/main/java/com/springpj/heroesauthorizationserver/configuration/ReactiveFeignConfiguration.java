package com.springpj.heroesauthorizationserver.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import reactivefeign.spring.config.EnableReactiveFeignClients;


@Configuration
@EnableWebFlux
@EnableReactiveFeignClients(basePackages = "com.springpj.heroesauthorizationserver.client")
public class ReactiveFeignConfiguration {
}
