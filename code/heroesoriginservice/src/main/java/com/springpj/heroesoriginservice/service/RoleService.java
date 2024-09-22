package com.springpj.heroesoriginservice.service;

import java.util.Set;

import com.springpj.heroesoriginservice.model.dto.AuthorityDto;
import com.springpj.heroesoriginservice.model.dto.role.RoleDto;

public interface RoleService {
	
	RoleDto findById(Long id);
	RoleDto findByName(String name);
	Set<AuthorityDto> findAllAuthoritiesByRoleId(Long id);

}
