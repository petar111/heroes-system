package com.springpj.heroescontentcreator.service;

import com.springpj.heroescontentcreator.model.dto.role.RoleDto;

public interface RoleService {
	
	RoleDto findById(Long id);
	RoleDto findByName(String name);

}
