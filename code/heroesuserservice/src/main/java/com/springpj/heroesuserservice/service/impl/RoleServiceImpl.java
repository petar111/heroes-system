package com.springpj.heroesuserservice.service.impl;


import com.springpj.heroesuserservice.errorhandler.exception.RoleNotFoundByIdException;
import com.springpj.heroesuserservice.errorhandler.exception.RoleNotFoundByNameException;
import com.springpj.heroesuserservice.mapper.AuthorityMapper;
import com.springpj.heroesuserservice.mapper.RoleMapper;
import com.springpj.heroesuserservice.model.authorization.Authority;
import com.springpj.heroesuserservice.model.authorization.Role;
import com.springpj.heroesuserservice.model.dto.AuthorityDto;
import com.springpj.heroesuserservice.model.dto.role.RoleDto;
import com.springpj.heroesuserservice.repository.RoleRepository;
import com.springpj.heroesuserservice.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;
    
    private final AuthorityMapper authorityMapper;

    public RoleServiceImpl(
    		RoleRepository roleRepository,
    		RoleMapper roleMapper, AuthorityMapper authorityMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
		this.authorityMapper = authorityMapper;
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

	@Override
	public Set<AuthorityDto> findAllAuthoritiesByRoleId(Long id) {
		Role role  = roleRepository.findById(id)
						.orElseThrow(() -> new RoleNotFoundByIdException(id));
		
		Set<Authority> authorities = role.getAuthorities();
		return authorityMapper.toDtoSet(authorities);
	}



}
