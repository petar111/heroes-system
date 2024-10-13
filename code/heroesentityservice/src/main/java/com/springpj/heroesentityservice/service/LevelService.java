package com.springpj.heroesentityservice.service;

import com.springpj.heroesentityservice.model.dto.CreatureDto;
import com.springpj.heroesentityservice.model.dto.EntityDefinitionDto;
import com.springpj.heroesentityservice.model.dto.HeroDto;
import com.springpj.heroesentityservice.model.dto.LevelDto;

public interface LevelService {
	
	LevelDto save(LevelDto dto);
	LevelDto findById(Long id);


}
