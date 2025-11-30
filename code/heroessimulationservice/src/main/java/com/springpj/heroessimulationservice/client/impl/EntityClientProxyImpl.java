package com.springpj.heroessimulationservice.client.impl;

import com.springpj.heroessimulationservice.client.EntityClientProxy;
import com.springpj.heroessimulationservice.model.entity.CreatureDto;
import com.springpj.heroessimulationservice.model.entity.EntityDefinitionDto;
import com.springpj.heroessimulationservice.model.entity.HeroDto;
import reactor.core.publisher.Mono;

import java.util.Map;

public class EntityClientProxyImpl implements EntityClientProxy {

    private final Map<Long, EntityDefinitionDto> entities;

    public EntityClientProxyImpl(Map<Long, EntityDefinitionDto> entities) {
        this.entities = entities;
    }

    public Map<Long, EntityDefinitionDto> getEntities() {
        return entities;
    }

    @Override
    public Mono<HeroDto> findHeroById(Long id) {
        return null;
    }

    @Override
    public Mono<CreatureDto> findCreatureById(Long id) {
        return null;
    }

    @Override
    public Mono<EntityDefinitionDto> findEntityById(Long id) {
        return Mono.just(entities.get(id));
    }
}
