package com.springpj.heroesuserservice.mapper;


import com.springpj.heroesuserservice.model.authorization.Authority;
import com.springpj.heroesuserservice.model.dto.AuthorityDto;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface AuthorityMapper {
	
	AuthorityDto toDto(Authority authority);
	Authority toEntity(AuthorityDto authorityDto);
	
	Set<AuthorityDto>  toDtoSet(Set<Authority> authority);
	Set<Authority> toEntity(Set<AuthorityDto> authorityDto);

}
