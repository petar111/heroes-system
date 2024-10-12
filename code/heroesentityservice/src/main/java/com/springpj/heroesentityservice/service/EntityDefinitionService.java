package com.springpj.heroesentityservice.service;

import com.springpj.heroesentityservice.model.dto.EntityDefinitionDto;

public interface EntityDefinitionService {
	
	EntityDefinitionDto save(EntityDefinitionDto dto);

	EntityDefinitionDto findById(Long id);

}
