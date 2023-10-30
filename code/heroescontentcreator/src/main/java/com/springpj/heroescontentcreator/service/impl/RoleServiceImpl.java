package com.springpj.heroescontentcreator.service.impl;

import org.springframework.stereotype.Service;

import com.springpj.heroescontentcreator.errorhandler.exception.RoleNotFoundByIdException;
import com.springpj.heroescontentcreator.errorhandler.exception.RoleNotFoundByNameException;
import com.springpj.heroescontentcreator.mapper.RoleMapper;
import com.springpj.heroescontentcreator.model.authorization.Role;
import com.springpj.heroescontentcreator.model.dto.role.RoleDto;
import com.springpj.heroescontentcreator.repository.RoleRepository;
import com.springpj.heroescontentcreator.service.RoleService;


@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleServiceImpl(
    		RoleRepository roleRepository,
    		RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

	@Override
	public RoleDto findById(Long id) {
		Role role = roleRepository.findById(id)
				.orElseThrow(() -> new RoleNotFoundByIdException(id));
		return roleMapper.toDto(role);
	}

	@Override
	public RoleDto findByName(String name) {
		Role role = roleRepository.findByName(name)
				.orElseThrow(() -> new RoleNotFoundByNameException(name));
		return roleMapper.toDto(role);
	}



}
