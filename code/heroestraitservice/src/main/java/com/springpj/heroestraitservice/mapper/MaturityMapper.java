package com.springpj.heroestraitservice.mapper;

import com.springpj.heroestraitservice.model.maturity.MaturityDto;
import com.springpj.heroestraitservice.model.maturity.Maturity;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface MaturityMapper {
	
	MaturityDto toDto(Maturity trait);
	Maturity toEntity(MaturityDto traitDto);

	List<Maturity> toEntityList(List<MaturityDto> traitDtos);
	List<MaturityDto> toDtoList(List<Maturity> traits);

}
