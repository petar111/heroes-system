package com.springpj.heroesbattletypeservice.service.impl;

import com.springpj.heroesbattletypeservice.errorhandler.exception.BattleTypeNotFoundByIdException;
import com.springpj.heroesbattletypeservice.mapper.BattleCapacityMapper;
import com.springpj.heroesbattletypeservice.mapper.BattleTypeMapper;
import com.springpj.heroesbattletypeservice.model.battletype.BattleCapacity;
import com.springpj.heroesbattletypeservice.model.battletype.BattleType;
import com.springpj.heroesbattletypeservice.model.dto.BattleCapacityDto;
import com.springpj.heroesbattletypeservice.model.dto.BattleTypeDto;
import com.springpj.heroesbattletypeservice.repository.BattleCapacityRepository;
import com.springpj.heroesbattletypeservice.repository.BattleTypeRepository;
import com.springpj.heroesbattletypeservice.service.BattleTypeService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BattleTypeServiceImpl implements BattleTypeService {


	private static final Logger log = LoggerFactory.getLogger(BattleTypeServiceImpl.class);

	private final BattleTypeRepository battleTypeRepository;
	private final BattleCapacityRepository battleCapacityRepository;
	private final BattleTypeMapper battleTypeMapper;
	private final BattleCapacityMapper battleCapacityMapper;

	@Autowired
	public BattleTypeServiceImpl(BattleTypeRepository battleTypeRepository,
								 BattleCapacityRepository battleCapacityRepository, BattleTypeMapper battleTypeMapper, BattleCapacityMapper battleCapacityMapper) {

		this.battleTypeRepository = battleTypeRepository;
		this.battleCapacityRepository = battleCapacityRepository;
		this.battleTypeMapper = battleTypeMapper;
		this.battleCapacityMapper = battleCapacityMapper;
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

	@Override
	public List<BattleCapacityDto> bulkAddCapacities(List<BattleCapacityDto> battleCapacities) {

		List<BattleCapacity> savedBattleCapacities = battleCapacityRepository.saveAll(battleCapacityMapper.toEntityList(battleCapacities));

		return battleCapacityMapper.toDtoList(savedBattleCapacities);
	}


}
