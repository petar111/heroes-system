package com.springpj.heroesoriginservice.service.impl;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.springpj.heroesoriginservice.errorhandler.exception.RoleNotFoundByIdException;
import com.springpj.heroesoriginservice.errorhandler.exception.RoleNotFoundByNameException;
import com.springpj.heroesoriginservice.mapper.AuthorityMapper;
import com.springpj.heroesoriginservice.mapper.RoleMapper;
import com.springpj.heroesoriginservice.model.authorization.Authority;
import com.springpj.heroesoriginservice.model.authorization.Role;
import com.springpj.heroesoriginservice.model.dto.AuthorityDto;
import com.springpj.heroesoriginservice.model.dto.role.RoleDto;
import com.springpj.heroesoriginservice.repository.RoleRepository;
import com.springpj.heroesoriginservice.service.RoleService;


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
