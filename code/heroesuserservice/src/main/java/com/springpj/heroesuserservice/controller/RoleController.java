package com.springpj.heroesuserservice.controller;

import com.springpj.heroesuserservice.model.dto.AuthorityDto;
import com.springpj.heroesuserservice.model.dto.role.RoleDto;
import com.springpj.heroesuserservice.service.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("role")
public class RoleController {

	private final RoleService roleService;

	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}

	@GetMapping("{id}")
	public RoleDto findById(@PathVariable Long id) {
		return roleService.findById(id);
	}
	
	@GetMapping("{id}/authorities")
	public Set<AuthorityDto> findAllAuthoritiesByRoleId(@PathVariable Long id) {
		return roleService.findAllAuthoritiesByRoleId(id);
	}
	
	@GetMapping("/name/{name}")
	public RoleDto findByName(@PathVariable String name) {
		return roleService.findByName(name);
	}

}
