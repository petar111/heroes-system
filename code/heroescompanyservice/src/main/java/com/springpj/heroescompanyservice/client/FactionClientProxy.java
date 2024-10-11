package com.springpj.heroescompanyservice.client;

import com.springpj.heroescompanyservice.model.dto.FactionDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

@Component
@FeignClient(name = "heroes-faction-service")
public interface FactionClientProxy {

	@GetMapping("/faction/{id}")
	FactionDto findById(@PathVariable Long id);

	
}
