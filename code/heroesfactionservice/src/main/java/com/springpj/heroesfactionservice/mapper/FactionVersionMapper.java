package com.springpj.heroesfactionservice.mapper;

import java.util.List;

import com.springpj.heroesfactionservice.model.dto.FactionVersionDto;
import com.springpj.heroesfactionservice.model.version.FactionVersion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = {FactionMapper.class})
public interface FactionVersionMapper {
	
	@Mapping(target = "faction", ignore = true)
	FactionVersionDto toDto(FactionVersion faction);
	FactionVersion toEntity(FactionVersionDto factionDto);
	
	List<FactionVersion> toEntityList(List<FactionVersionDto> factionDtos);
	List<FactionVersionDto> toDtoList(List<FactionVersion> factions);

}
