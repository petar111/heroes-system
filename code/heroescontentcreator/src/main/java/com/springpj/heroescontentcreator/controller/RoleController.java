package com.springpj.heroescontentcreator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springpj.heroescontentcreator.model.dto.role.RoleDto;
import com.springpj.heroescontentcreator.service.RoleService;

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
	
	@GetMapping("/name/{name}")
	public RoleDto findByName(@PathVariable String name) {
		return roleService.findByName(name);
	}

}
