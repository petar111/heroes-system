package com.springpj.heroesentityservice.configuration;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.springpj.heroesentityservice.client")
public class FeignConfiguration {
}
