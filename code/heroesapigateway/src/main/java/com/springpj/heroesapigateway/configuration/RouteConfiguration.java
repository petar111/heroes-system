package com.springpj.heroesapigateway.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;

@Configuration
public class RouteConfiguration {
	
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Bean
	public RouteLocator routeLocator(RouteLocatorBuilder builder) {

		return builder
				.routes()
				.route(r -> r.path("/**")
						.filters(f -> f.filter(
								(exchange, chain) -> {
									ServerHttpRequest request = exchange.getRequest();
									
									log.info("Original URI: {}", request.getURI());
									
									return chain.filter(exchange);
								}
								))
						.uri("lb://heroes-authorization-server/authenticate"))
//				.route(r -> r.path("/get")
//						.filters(f -> f.addRequestHeader("MyRequestHEADER", "MyRequestHeaderVALUE")
//								.addRequestParameter("addedParam", "paramValue")
//								.addResponseHeader("MyResponseHeader", "MyResponseHeaderValue"))
//						.uri("http://httpbin.org:80"))
//				.route("currency-exchange", r -> r.path("/currency-exchange/**").uri("lb://currency-exchange"))
//				.route("currency-conversion", r -> r.path("/currency-conversion/**").uri("lb://currency-conversion"))
//				.route("currency-conversion-new-something-fake",
//						r -> r.path("/currency-conversion-new/**")
//								.filters(f -> f.rewritePath("/currency-conversion-new/", "/currency-conversion/"))
//								.uri("lb://currency-conversion"))
//				.route("currency-conversion-new-with-segment",
//						r -> r.path("/currency-conversion-new-with-segment/**")
//								.filters(f -> f.rewritePath("/currency-conversion-new-with-segment/(?<segment>/?.*)",
//										"/currency-conversion/${segment}"))
//								.uri("lb://currency-conversion"))
				.build();
	}

}
