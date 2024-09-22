package com.springpj.heroesoriginservice.mapper;

import java.util.Set;

import org.mapstruct.Mapper;

import com.springpj.heroesoriginservice.model.authorization.Authority;
import com.springpj.heroesoriginservice.model.dto.AuthorityDto;

@Mapper(componentModel = "spring")
public interface AuthorityMapper {
	
	AuthorityDto toDto(Authority authority);
	Authority toEntity(AuthorityDto authorityDto);
	
	Set<AuthorityDto>  toDtoSet(Set<Authority> authority);
	Set<Authority> toEntity(Set<AuthorityDto> authorityDto);

}
