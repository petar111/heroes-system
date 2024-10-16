package com.springpj.heroessimulationservice.client;

import com.springpj.heroessimulationservice.model.battlecapacity.BattleCapacityDto;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactivefeign.spring.config.ReactiveFeignClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@ReactiveFeignClient(name = "heroes-battletype-service", url = "heroes-battletype-service:8011")
public interface BattleTypeClientProxy {

    @GetMapping("/capacity/entity/{id}")
    Mono<List<BattleCapacityDto>> getCapacitiesByEntityId(@PathVariable Long id);
}
