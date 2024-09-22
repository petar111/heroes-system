package com.springpj.heroesapigateway.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RouteConfiguration {
	
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder) {

		return builder
				.routes()
				// .route("heroes-authorization-server", r -> r.path("/heroes-authorization-server/**")
				// 		.uri("lb://heroes-authorization-server"))
				// .route("heroes-content-creator", r -> r.path("/heroes-content-creator/**")
				// 		.uri("lb://heroes-content-creator"))
				.build();
	}

}
