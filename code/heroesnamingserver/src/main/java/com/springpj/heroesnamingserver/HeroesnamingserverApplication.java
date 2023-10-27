package com.springpj.heroesnamingserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class HeroesnamingserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeroesnamingserverApplication.class, args);
	}

}
