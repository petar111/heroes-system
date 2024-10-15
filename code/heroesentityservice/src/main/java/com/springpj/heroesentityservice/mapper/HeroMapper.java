package com.springpj.heroesentityservice.mapper;

import com.springpj.heroesentityservice.model.entity.HeroDto;
import com.springpj.heroesentityservice.model.entity.Hero;
import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface HeroMapper {
	
	HeroDto toDto(Hero hero);
	Hero toEntity(HeroDto heroDto);

	List<Hero> toEntityList(List<HeroDto> heroDtos);
	List<HeroDto> toDtoList(List<Hero> heros);

}
