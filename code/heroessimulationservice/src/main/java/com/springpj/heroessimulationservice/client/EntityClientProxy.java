package com.springpj.heroessimulationservice.client;

import com.springpj.heroessimulationservice.model.entity.CreatureDto;
import com.springpj.heroessimulationservice.model.entity.EntityDefinitionDto;
import com.springpj.heroessimulationservice.model.entity.HeroDto;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Mono;

@Component
@ReactiveFeignClient(name = "heroes-entity-service", url = "heroes-entitiy-service:8015")
public interface EntityClientProxy {

	@GetMapping("/hero/{id}")
	Mono<HeroDto> findHeroById(@PathVariable Long id);

	@GetMapping("/creature/{id}")
	Mono<CreatureDto> findCreatureById(@PathVariable Long id);

    @GetMapping("/entity/{id}")
    Mono<EntityDefinitionDto> findEntityById(@PathVariable Long id);

	
}
