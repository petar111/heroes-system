package com.springpj.heroesauthorizationserver.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientConfiguration {

    @Autowired
    private Environment environment;

    @Bean("userServiceClient")
    public WebClient userServiceClient(){
        return WebClient.builder()
                .baseUrl(String.format("%s%s", environment.getProperty("services.apiGateway.baseUrl"), environment.getProperty("services.heroesUserService.name")))
                .build();
    }

}
