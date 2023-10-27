package com.springpj.heroesauthorizationserver.configuration;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.springpj.heroesauthorizationserver.client")
public class FeignConfiguration {

}
