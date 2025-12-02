package com.springpj.heroesentityservice.service;

import com.springpj.heroesentityservice.model.entity.CreatureDto;
import com.springpj.heroesentityservice.model.entity.EntityDefinitionDto;
import com.springpj.heroesentityservice.model.entity.HeroDto;

import java.util.List;

public interface EntityService {
	
	EntityDefinitionDto save(EntityDefinitionDto dto);

	EntityDefinitionDto findById(Long id);

	HeroDto findHeroById(Long id);
	HeroDto saveHero(HeroDto dto);

	CreatureDto findCreatureById(Long id);
	CreatureDto saveCreature(CreatureDto dto);


    List<EntityDefinitionDto> saveAll(List<EntityDefinitionDto> dtos);
}
