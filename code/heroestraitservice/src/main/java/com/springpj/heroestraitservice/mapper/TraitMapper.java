package com.springpj.heroestraitservice.mapper;
import com.springpj.heroestraitservice.model.trait.Trait;
import com.springpj.heroestraitservice.model.dto.TraitDto;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface TraitMapper {
	
	TraitDto toDto(Trait trait);
	Trait toEntity(TraitDto traitDto);

	List<Trait> toEntityList(List<TraitDto> traitDtos);
	List<TraitDto> toDtoList(List<Trait> traits);

}
