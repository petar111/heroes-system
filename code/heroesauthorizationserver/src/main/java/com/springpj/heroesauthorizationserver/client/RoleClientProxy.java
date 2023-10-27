package com.springpj.heroesauthorizationserver.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.springpj.heroesauthorizationserver.dto.RoleDto;

@FeignClient(name = ClientConstants.HEROES_CONTENT_CREATOR_APP_NAME, contextId = "role-client-proxy")
public interface RoleClientProxy {
	
	@GetMapping("/role/{name}")
	RoleDto findByName(String name);

}
