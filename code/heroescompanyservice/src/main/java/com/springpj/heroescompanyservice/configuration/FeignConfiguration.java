package com.springpj.heroescompanyservice.configuration;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.springpj.heroescompanyservice.client")
public class FeignConfiguration {
}
