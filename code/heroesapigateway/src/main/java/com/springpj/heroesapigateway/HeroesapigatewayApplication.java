package com.springpj.heroesapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class HeroesapigatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeroesapigatewayApplication.class, args);
	}

}
