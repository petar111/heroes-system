package com.springpj.heroescompanyservice.client;

import com.springpj.heroescompanyservice.model.dto.FactionDto;
import com.springpj.heroescompanyservice.model.dto.HeroDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(name = "heroes-entity-service")
public interface EntityClientProxy {

	@GetMapping("/hero/{id}")
	HeroDto findHeroById(@PathVariable Long id);

	@GetMapping("/creature/{id}")
	HeroDto findCreatureById(@PathVariable Long id);

	
}
