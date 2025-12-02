package com.springpj.heroesentityservice.service.impl;

import com.springpj.heroesentityservice.client.BattleTypeClientProxy;
import com.springpj.heroesentityservice.errorhandler.exception.CreatureNotFoundByIdException;
import com.springpj.heroesentityservice.errorhandler.exception.EntityDefinitionNotFoundByIdException;
import com.springpj.heroesentityservice.errorhandler.exception.HeroNotFoundByIdException;
import com.springpj.heroesentityservice.mapper.CreatureMapper;
import com.springpj.heroesentityservice.mapper.EntityDefinitionMapper;
import com.springpj.heroesentityservice.mapper.HeroMapper;
import com.springpj.heroesentityservice.model.battlecapacity.BattleCapacityDto;
import com.springpj.heroesentityservice.model.entity.CreatureDto;
import com.springpj.heroesentityservice.model.entity.HeroDto;
import com.springpj.heroesentityservice.model.entity.Creature;
import com.springpj.heroesentityservice.model.entity.EntityDefinition;
import com.springpj.heroesentityservice.model.entity.EntityDefinitionDto;
import com.springpj.heroesentityservice.model.entity.Hero;
import com.springpj.heroesentityservice.repository.CreatureRepository;
import com.springpj.heroesentityservice.repository.EntityDefinitionRepository;
import com.springpj.heroesentityservice.repository.HeroRepository;
import com.springpj.heroesentityservice.service.EntityService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class EntityServiceImpl implements EntityService {


	private static final Logger log = LoggerFactory.getLogger(EntityServiceImpl.class);

	private final EntityDefinitionRepository entityDefinitionRepository;
	private final HeroRepository heroRepository;
	private final CreatureRepository creatureRepository;
	private final EntityDefinitionMapper entityDefinitionMapper;
	private final HeroMapper heroMapper;
	private final CreatureMapper creatureMapper;

	private final BattleTypeClientProxy battleTypeClientProxy;

	@Autowired
	public EntityServiceImpl(EntityDefinitionRepository entityDefinitionRepository,
							 HeroRepository heroRepository, CreatureRepository creatureRepository, EntityDefinitionMapper entityDefinitionMapper, HeroMapper heroMapper, CreatureMapper creatureMapper, BattleTypeClientProxy battleTypeClientProxy) {

		this.entityDefinitionRepository = entityDefinitionRepository;
		this.heroRepository = heroRepository;
		this.creatureRepository = creatureRepository;
		this.entityDefinitionMapper = entityDefinitionMapper;
		this.heroMapper = heroMapper;
		this.creatureMapper = creatureMapper;
		this.battleTypeClientProxy = battleTypeClientProxy;
	}

	@Override
	public EntityDefinitionDto save(EntityDefinitionDto dto) {

		log.info("Saving entityDefinition with name {} - START", dto.getName());
		EntityDefinition savedEntityDefinition = entityDefinitionRepository.save(entityDefinitionMapper.toEntity(dto));
		addEntityBattleCapacities(savedEntityDefinition, dto);
		log.info("Saving entityDefinition with name {} - DONE", dto.getName());

		EntityDefinitionDto entityDefinitionDto = entityDefinitionMapper.toDto(savedEntityDefinition);

		return entityDefinitionDto;
	}

    @Override
    public List<EntityDefinitionDto> saveAll(List<EntityDefinitionDto> dtos) {
        log.info("Saving entityDefinitions  - START");
        Map<String, List<BattleCapacityDto>> battleCapacitiesMap = dtos.stream().collect(Collectors.toMap(EntityDefinitionDto::getName, EntityDefinitionDto::getBattleCapacities));
        List<EntityDefinition> entities = entityDefinitionRepository.saveAll(entityDefinitionMapper.toEntityList(dtos));
        entities.forEach(e -> battleCapacitiesMap.get(e.getName()).forEach(bc -> bc.setEntityId(e.getId())));
        saveBattleCapacities(battleCapacitiesMap.values().stream().flatMap(Collection::stream).toList());
        log.info("Saving entityDefinitions - DONE");


        return entityDefinitionMapper.toDtoList(entities);
    }

    private void saveBattleCapacities(List<BattleCapacityDto> list) {
        battleTypeClientProxy.bulkAddCapacities(list);
    }

    @Override
	public EntityDefinitionDto findById(Long id) {
		EntityDefinition entityDefinition = entityDefinitionRepository.findById(id)
				.orElseThrow(() -> new EntityDefinitionNotFoundByIdException(id));


        EntityDefinitionDto dto = entityDefinitionMapper.toDto(entityDefinition);
        dto.setBattleCapacities(battleTypeClientProxy.findCapacitiesByEntityId(id));
		return dto;
	}

	@Override
	public HeroDto findHeroById(Long id) {
		Hero hero = heroRepository.findById(id)
				.orElseThrow(() -> new HeroNotFoundByIdException(id));
		return heroMapper.toDto(hero);
	}

	@Override
	public HeroDto saveHero(HeroDto dto) {
		log.info("Saving hero with name {} - START", dto.getName());
		Hero savedHero = heroRepository.save(heroMapper.toEntity(dto));
		addEntityBattleCapacities(savedHero, dto);
		log.info("Saving hero with name {} - DONE", dto.getName());

		HeroDto heroDto = heroMapper.toDto(savedHero);

		return heroDto;
	}

	@Override
	public CreatureDto findCreatureById(Long id) {
		Creature creature = creatureRepository.findById(id)
				.orElseThrow(() -> new CreatureNotFoundByIdException(id));
		return creatureMapper.toDto(creature);
	}

	@Override
	public CreatureDto saveCreature(CreatureDto dto) {
		log.info("Saving creature with name {} - START", dto.getName());
		Creature savedCreature = creatureRepository.save(creatureMapper.toEntity(dto));
		addEntityBattleCapacities(savedCreature, dto);
		log.info("Saving hero with name {} - DONE", dto.getName());

		CreatureDto creatureDto = creatureMapper.toDto(savedCreature);

		return creatureDto;
	}

    private void addEntityBattleCapacities(EntityDefinition savedEntity, EntityDefinitionDto dto){
		dto.getBattleCapacities().forEach(c -> c.setEntityId(savedEntity.getId()));
		battleTypeClientProxy.bulkAddCapacities(dto.getBattleCapacities());
	}


}
