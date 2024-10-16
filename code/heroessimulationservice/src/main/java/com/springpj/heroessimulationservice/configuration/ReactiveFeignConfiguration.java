package com.springpj.heroessimulationservice.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import reactivefeign.spring.config.EnableReactiveFeignClients;


@Configuration
@EnableWebFlux
@EnableReactiveFeignClients(basePackages = "com.springpj.heroessimulationservice.client")
public class ReactiveFeignConfiguration {
}
