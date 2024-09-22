package com.springpj.heroesuserservice.service;



import com.springpj.heroesuserservice.model.dto.AuthorityDto;
import com.springpj.heroesuserservice.model.dto.role.RoleDto;

import java.util.Set;

public interface RoleService {
	
	RoleDto findById(Long id);
	RoleDto findByName(String name);
	Set<AuthorityDto> findAllAuthoritiesByRoleId(Long id);

}
