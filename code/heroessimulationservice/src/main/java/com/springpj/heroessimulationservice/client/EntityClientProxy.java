package com.springpj.heroessimulationservice.client;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@Component
@ReactiveFeignClient(name = "heroes-entity-service", url = "heroes-entitiy-service:8015")
public interface EntityClientProxy {

	@GetMapping("/hero/{id}")
	Mono<Void> findHeroById(@PathVariable Long id);

	@GetMapping("/creature/{id}")
	Mono<Void> findCreatureById(@PathVariable Long id);

	
}
