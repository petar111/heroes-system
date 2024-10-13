package com.springpj.heroesentityservice.mapper;

import com.springpj.heroesentityservice.model.dto.CreatureDto;
import com.springpj.heroesentityservice.model.entity.Creature;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CreatureMapper {
	
	CreatureDto toDto(Creature creature);
	Creature toEntity(CreatureDto creatureDto);

	List<Creature> toEntityList(List<CreatureDto> creatureDtos);
	List<CreatureDto> toDtoList(List<Creature> creatures);

}
