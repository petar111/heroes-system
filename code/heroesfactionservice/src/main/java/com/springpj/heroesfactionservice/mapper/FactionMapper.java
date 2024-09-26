package com.springpj.heroesfactionservice.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.springpj.heroesfactionservice.model.dto.FactionDto;
import com.springpj.heroesfactionservice.model.faction.Faction;
import com.springpj.heroesfactionservice.model.version.FactionVersion;

@Mapper(componentModel = "spring")
public interface FactionMapper {
	
	FactionDto toDto(Faction faction);
	Faction toEntity(FactionDto factionDto);
	
	@Mapping(source = ".", target = "faction")
	@Mapping(target = "id", ignore = true)
	@Mapping(target = "dateCreated", ignore = true)
	FactionVersion toVersionEntity(Faction faction);
	
	List<Faction> toEntityList(List<FactionDto> factionDtos);
	List<FactionDto> toDtoList(List<Faction> factions);

}
