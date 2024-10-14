package com.springpj.heroesspellservice.mapper;
import com.springpj.heroesspellservice.model.spell.Spell;
import com.springpj.heroesspellservice.model.dto.SpellDto;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface SpellMapper {
	
	SpellDto toDto(Spell spell);
	Spell toEntity(SpellDto spellDto);

	List<Spell> toEntityList(List<SpellDto> spellDtos);
	List<SpellDto> toDtoList(List<Spell> spells);

}
