package com.springpj.heroesentityservice.service;

import com.springpj.heroesentityservice.model.dto.CreatureDto;
import com.springpj.heroesentityservice.model.dto.EntityDefinitionDto;
import com.springpj.heroesentityservice.model.dto.HeroDto;
import com.springpj.heroesentityservice.model.entity.Hero;

public interface EntityService {
	
	EntityDefinitionDto save(EntityDefinitionDto dto);

	EntityDefinitionDto findById(Long id);

	HeroDto findHeroById(Long id);
	HeroDto saveHero(HeroDto dto);

	CreatureDto findCreatureById(Long id);
	CreatureDto saveCreature(CreatureDto dto);


}
