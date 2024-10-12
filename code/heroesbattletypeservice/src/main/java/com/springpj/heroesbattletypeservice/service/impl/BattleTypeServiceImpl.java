package com.springpj.heroesbattletypeservice.service.impl;

import com.springpj.heroesbattletypeservice.errorhandler.exception.BattleTypeNotFoundByIdException;
import com.springpj.heroesbattletypeservice.mapper.BattleTypeMapper;
import com.springpj.heroesbattletypeservice.model.battletype.BattleType;
import com.springpj.heroesbattletypeservice.model.dto.BattleTypeDto;
import com.springpj.heroesbattletypeservice.repository.BattleTypeRepository;
import com.springpj.heroesbattletypeservice.service.BattleTypeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BattleTypeServiceImpl implements BattleTypeService {


	private static final Logger log = LoggerFactory.getLogger(BattleTypeServiceImpl.class);

	private final BattleTypeRepository battleTypeRepository;
	private final BattleTypeMapper battleTypeMapper;

	@Autowired
	public BattleTypeServiceImpl(BattleTypeRepository battleTypeRepository,
								 BattleTypeMapper battleTypeMapper) {

		this.battleTypeRepository = battleTypeRepository;
		this.battleTypeMapper = battleTypeMapper;
	}

	@Override
	public BattleTypeDto save(BattleTypeDto dto) {

		log.info("Saving battleType with name {} - START", dto.getName());
		BattleType savedBattleType = battleTypeRepository.save(battleTypeMapper.toEntity(dto));
		log.info("Saving battleType with name {} - DONE", dto.getName());

		BattleTypeDto battleTypeDto = battleTypeMapper.toDto(savedBattleType);

		return battleTypeDto;
	}

	@Override
	public BattleTypeDto findById(Long id) {
		BattleType battleType = battleTypeRepository.findById(id)
				.orElseThrow(() -> new BattleTypeNotFoundByIdException(id));
		return battleTypeMapper.toDto(battleType);
	}


}
