package com.springpj.heroesauthorizationserver.configuration;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;

@Configuration
@EnableFeignClients(basePackages = "com.springpj.heroesauthorizationserver.client")
public class FeignConfiguration {

	@Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.HEADERS;
    }
}
