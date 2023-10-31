package com.springpj.heroescontentcreator.mapper;

import java.util.Set;

import org.mapstruct.Mapper;

import com.springpj.heroescontentcreator.model.authorization.Authority;
import com.springpj.heroescontentcreator.model.dto.AuthorityDto;

@Mapper(componentModel = "spring")
public interface AuthorityMapper {
	
	AuthorityDto toDto(Authority authority);
	Authority toEntity(AuthorityDto authorityDto);
	
	Set<AuthorityDto>  toDtoSet(Set<Authority> authority);
	Set<Authority> toEntity(Set<AuthorityDto> authorityDto);

}
