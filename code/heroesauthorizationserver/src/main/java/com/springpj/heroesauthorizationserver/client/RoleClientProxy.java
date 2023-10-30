package com.springpj.heroesauthorizationserver.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.springpj.heroesauthorizationserver.dto.RoleDto;

@FeignClient(name = ClientConstants.HEROES_CONTENT_CREATOR_APP_NAME, contextId = "role-client-proxy")
public interface RoleClientProxy {
	
	@GetMapping("/role/name/{name}")
	RoleDto findByName(@PathVariable String name);

}
