package com.springpj.heroesentityservice.service.impl;

import com.springpj.heroesentityservice.errorhandler.exception.EntityDefinitionNotFoundByIdException;
import com.springpj.heroesentityservice.mapper.EntityDefinitionMapper;
import com.springpj.heroesentityservice.model.battletype.EntityDefinition;
import com.springpj.heroesentityservice.model.dto.EntityDefinitionDto;
import com.springpj.heroesentityservice.repository.EntityDefinitionRepository;
import com.springpj.heroesentityservice.service.EntityDefinitionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EntityDefinitionServiceImpl implements EntityDefinitionService {


	private static final Logger log = LoggerFactory.getLogger(EntityDefinitionServiceImpl.class);

	private final EntityDefinitionRepository entityDefinitionRepository;
	private final EntityDefinitionMapper entityDefinitionMapper;

	@Autowired
	public EntityDefinitionServiceImpl(EntityDefinitionRepository entityDefinitionRepository,
								 EntityDefinitionMapper entityDefinitionMapper) {

		this.entityDefinitionRepository = entityDefinitionRepository;
		this.entityDefinitionMapper = entityDefinitionMapper;
	}

	@Override
	public EntityDefinitionDto save(EntityDefinitionDto dto) {

		log.info("Saving entityDefinition with name {} - START", dto.getName());
		EntityDefinition savedEntityDefinition = entityDefinitionRepository.save(entityDefinitionMapper.toEntity(dto));
		log.info("Saving entityDefinition with name {} - DONE", dto.getName());

		EntityDefinitionDto entityDefinitionDto = entityDefinitionMapper.toDto(savedEntityDefinition);

		return entityDefinitionDto;
	}

	@Override
	public EntityDefinitionDto findById(Long id) {
		EntityDefinition entityDefinition = entityDefinitionRepository.findById(id)
				.orElseThrow(() -> new EntityDefinitionNotFoundByIdException(id));
		return entityDefinitionMapper.toDto(entityDefinition);
	}


}
