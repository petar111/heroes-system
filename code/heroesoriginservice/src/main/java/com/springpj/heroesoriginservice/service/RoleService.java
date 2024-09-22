package com.springpj.heroescontentcreator.service;

import java.util.Set;

import com.springpj.heroescontentcreator.model.dto.AuthorityDto;
import com.springpj.heroescontentcreator.model.dto.role.RoleDto;

public interface RoleService {
	
	RoleDto findById(Long id);
	RoleDto findByName(String name);
	Set<AuthorityDto> findAllAuthoritiesByRoleId(Long id);

}
